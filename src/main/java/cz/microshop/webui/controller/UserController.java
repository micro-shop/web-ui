package cz.microshop.webui.controller;

import cz.microshop.webui.helpers.FlashMessage;
import cz.microshop.webui.model.Email;
import cz.microshop.webui.model.PasswordResetToken;
import cz.microshop.webui.model.User;
import cz.microshop.webui.service.EmailService;
import cz.microshop.webui.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


@Controller
@RequestMapping("/user")
public class UserController {

	private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDetailsService securityService;
	@Autowired
    private UserService userService;
	@Autowired
	private EmailService emailService;

	@RequestMapping(value = "/remove")
	public String invalidateAccount(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		User signedIn = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
		try {
			request.logout();
		} catch(ServletException e) {
			FlashMessage.createFlashMessage("alert-danger", "Operaci nebylo možné dokončit. Akci opakujte později", redirectAttributes);
			return "redirect:/";
		}

		userService.disableUser(signedIn.getUsername());
		FlashMessage.createFlashMessage("alert-warning", "Váš účet byl zrušen.", redirectAttributes);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/savePassword", method = RequestMethod.POST)
	public String savePassword(Locale locale, @RequestParam("newPassword") String newPassword,
			@RequestParam("newPasswordConfirmation") String newPasswordConfirmation, Model model, 
			RedirectAttributes redirectAttributes) {
		
	    User user =
	      userService.findByUsername((String)SecurityContextHolder.getContext()
	                                  .getAuthentication().getName());
	    List<String> errorMessages = new ArrayList<>();
	    user.setPassword(newPassword);
	    //user.setPasswordConfirmation(newPasswordConfirmation);
	    userService.checkEqualityOfPasswords(user, errorMessages);
	    checkPasswordCorrectness(user, errorMessages);
	    if(errorMessages.size() > 0) {
	    	model.addAttribute("hasErrors", true);
	    	model.addAttribute("errorMessages", errorMessages);
	    	return "updatePassword";
	    }
	    FlashMessage.createFlashMessage("alert-success", "Vaše heslo bylo změněno.", redirectAttributes);
	    userService.updateUserPassword(user);
		System.out.println("User " + user.getUserId() + " role " + user.getUserRoles().toString() + " changedPass");
		SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities().stream().forEach(o -> System.out.println("Authority" + o.getAuthority()));
	    return "redirect:/";
	}	

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String showChangePasswordPage(Locale locale, RedirectAttributes redirectAttributes, 
			@RequestParam("id") long id, @RequestParam("token") String token) {
		
	    Boolean result = userService.validatePasswordResetToken(id, token);
	    if (result == null || Boolean.FALSE.equals(result)) {
	    	FlashMessage.createFlashMessage("alert-danger", "Heslo nebylo možné resetovat. Operaci opakujte později.", redirectAttributes);
	        return "redirect:/login?lang=" + locale.getLanguage();
	    } else {
			User user = userService.find(id);
			Authentication auth = new UsernamePasswordAuthenticationToken(
					user, null, Arrays.asList(
					new SimpleGrantedAuthority("CHANGE_PASSWORD_PRIVILEGE")));
			SecurityContextHolder.getContext().setAuthentication(auth);
			return "redirect:/user/updatePassword";
		}
	}
	
	@RequestMapping(value="/updatePassword")
	public String updatePassword() {
		return "updatePassword";
	}
	
	@RequestMapping(value="/update")
	public String updateUser(Model model) {
		
		User user = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
		model.addAttribute("user", user);
		return "singup";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String resetPassword(HttpServletRequest request, @RequestParam("email") String userEmail,
			RedirectAttributes redirectAttributes) {
		
		User user = userService.findByEmail(userEmail);
		if (user == null) {
			return "redirect:/";
		}
		PasswordResetToken token = userService.createPasswordResetTokenForUser(user, null);
		StringBuffer url = request.getRequestURL();
		String uri = request.getRequestURI();
		String host = url.substring(0, url.indexOf(uri));
		emailService.send(constructResetTokenEmail(host, token.getToken(), user));
		FlashMessage.createFlashMessage("alert-success", "Vaše heslo bylo změněno. Pro další postup následujte instrukce zaslané v emailu", redirectAttributes);
		return "redirect:/signin";
	}
	
	private Email constructResetTokenEmail(String contextPath, String token, User user) {
		
		String url = contextPath + "/user/changePassword?id=" +
				user.getUserId() + "&token=" + token;
		return constructEmail("Dobrý den. Pro resetování heslo klikněte na následující odkaz" + " \r\n" + url, user);
	}
	
	private Email constructEmail(String body, User user) {
		
		Email email = new Email();
		email.setContent(body);
		email.setTo(user.getEmail());
		return email;
	}
	
	private void checkPasswordCorrectness(User user, List<String> errorMessages) {
		
		if(!validatePassword(user.getPassword())) {//  || !validatePassword(user.getPasswordConfirmation())) {
			errorMessages.add("One of the passwords is invalid. It should contain at least one: digit, "
			+ "upper, lower case letter, special character and its length should be in range from 6 to 60 chars");
		}		
	}
	
	private boolean validatePassword(String password) {
		return true;
		//return password.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,60}");
	}
      
}

