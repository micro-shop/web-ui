package cz.microshop.webui.controller;

import cz.microshop.webui.dao.RoleDao;
import cz.microshop.webui.helpers.FlashMessage;
import cz.microshop.webui.model.Product;
import cz.microshop.webui.model.User;
import cz.microshop.webui.security.UserRole;
import cz.microshop.webui.service.CategoryService;
import cz.microshop.webui.service.ProductService;
import cz.microshop.webui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class HomeController {	
	
	@Autowired
	private RoleDao roleDao;
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;
    
	@RequestMapping("/")
	public String index(Model model) {
		List<Product> sixLatestProducts = productService.getAll();
		List<Product> promotedProducts = productService.getAll();
		model.addAttribute("products", sixLatestProducts);
		model.addAttribute("promotedProducts", promotedProducts);
		model.addAttribute("showJumbo", true);
		model.addAttribute("categories", categoryService.getCategories());
		return "shop";
	}
	
	@RequestMapping(value="/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword() {		
		return "forgotpassword";
	}
	
	@RequestMapping(value="/signin", method = RequestMethod.GET)
	public String signin() {
		return "signin";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(Model model) {		
		User user = new User();
		model.addAttribute("user", user);
		return "singup";
	}	
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String signupPost(@Valid @ModelAttribute("user") User user, BindingResult bindingResult ,
			Model model, RedirectAttributes redirectAttributes) {
		
		List<String> errorMessages = new ArrayList<>();
		User signedIn = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
		
		validateUniqueValues(user, errorMessages, signedIn);
		userService.checkEqualityOfPasswords(user, errorMessages);
		checkOtherValidationErrors(bindingResult, errorMessages);
		
		if (errorMessages.size() > 0) {
			setBootstrapAlertData(model, errorMessages);
			return "singup";
		} 
		
		if(signedIn == null) {
			saveNewUserWithStandardRole(user);
		} else {
			updateExistingUser(user, signedIn);
		}
		FlashMessage.createFlashMessage("alert-success", "Signed up successfully", redirectAttributes);
		return "redirect:/";
	}

	private void setBootstrapAlertData(Model model, List<String> errorMessages) {
		model.addAttribute("hasErrors", true);
		model.addAttribute("errorMessages", errorMessages);
	}

	private void saveNewUserWithStandardRole(User user) {
		Set<UserRole> userRoles = new HashSet<>();
		userRoles.add(new UserRole(user, roleDao.findByName("ROLE_USER")));
		userService.createUser(user, userRoles);
	}

	private void updateExistingUser(User user, User signedIn) {
		userService.updateUserPassword(user);
		userService.save(user);
		signedIn.setUsername(user.getUsername());
	}

	private void validateUniqueValues(User user, List<String> errorMessages, User signedIn) {
		if(signedIn == null) {
			validateUniqueValuesForNewUser(user, errorMessages);
		} else {
			validateUniqueValuesForExistingUser(user, errorMessages, signedIn);
		}
	}

	private void validateUniqueValuesForExistingUser(User user, List<String> errorMessages, User signedIn) {
		if(!user.getUsername().equals(signedIn.getUsername()))
			checkExistenceOfUsername(user, errorMessages);
		if(!user.getEmail().equals(signedIn.getEmail()))
			checkExistenceOfEmail(user, errorMessages);
		if(!user.getPhone().equals(signedIn.getPhone()))
			checkExistenceOfPhoneNumber(user, errorMessages);
	}

	private void validateUniqueValuesForNewUser(User user, List<String> errorMessages) {
		checkExistenceOfEmail(user, errorMessages);
		checkExistenceOfUsername(user, errorMessages);
		checkExistenceOfPhoneNumber(user, errorMessages);
	}

	private void checkOtherValidationErrors(BindingResult bindingResult, List<String> errorMessages) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error -> {
				errorMessages.add(error.getDefaultMessage());
			});
		}
	}

	private void checkExistenceOfPhoneNumber(User user, List<String> errorMessages) {
		if(userService.checkPhoneNumberExists(user.getPhone())) {
			errorMessages.add("Phone number exists in database.");
		}
	}

	private void checkExistenceOfUsername(User user, List<String> errorMessages) {
		if (userService.checkUsernameExists(user.getUsername())) {
			errorMessages.add("Username exists in database.");
		}
	}

	private void checkExistenceOfEmail(User user, List<String> errorMessages) {
		if (userService.checkEmailExists(user.getEmail())) {
			errorMessages.add("Email exists in database.");
		}
	}
}
