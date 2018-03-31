package cz.microshop.webui.controller;

import cz.microshop.webui.helpers.FlashMessage;
import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Order;
import cz.microshop.webui.model.Shipping;
import cz.microshop.webui.model.User;
import cz.microshop.webui.service.CartService;
import cz.microshop.webui.service.OrderService;
import cz.microshop.webui.service.ShippingService;
import cz.microshop.webui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShippingService shippingService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	/*@RequestMapping(value="/hook", method=RequestMethod.POST)
	public String paypalHook(Model model, HttpServletRequest httpRequest) {
		
		httpRequest.getParameterMap().forEach((key, value) -> {
			System.out.println("Key: " + key + " values: " + Arrays.toString(value));
		});
		return "thankyou";
	}*/
	
	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String checkout(Model model, HttpServletRequest httpRequest, @RequestParam("shipping") String shippingName) {
		Shipping shipping = shippingService.findByName(shippingName);
		Cart cart = cartService.findCart((Long)httpRequest.getSession().getAttribute("cart_id"));
		User user2 = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
		Order order = orderService.placeOrder(cart, shipping, user2);
		FlashMessage.createFlashMessage("info", "Your order has been placed successfully.", model);
		sendEmailMessageWithOrderList(order, user2);
		httpRequest.getSession().setAttribute("cartQuantity", cart.getItems().size());
		model.addAttribute("order", order);
		return "order_summary";
	}
	
	@RequestMapping(value = "/my_orders", method = RequestMethod.GET)
	public String showMyOrders(Model model, @RequestParam(name="page", defaultValue="0", required=false) Integer page) {
		Pageable pageable = new PageRequest(page, 5);
		User user2 = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
		List<Order> orders = orderService.userOrders(user2.getId());
		model.addAttribute("orders", orders);
		model.addAttribute("page", page);
		return "my_orders";
	}
	
	private void sendEmailMessageWithOrderList(Order order, User user2) {
		String mailContent = FlashMessage.createOrderContentsMessage(order, user2);
		Session session = null;
		MimeMessage mimeMessage = new MimeMessage(session);
		
		try {
			mimeMessage.setSubject("Online Shop. Purchase id: " + order.getId());
			mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(user2.getEmail(), "user2"));
			mimeMessage.setContent(mailContent, "text/html");
			mimeMessage.setFrom("ciprojektwimiip@gmail.com"); //TODO: UPDATE LATER
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		mailSender.send(mimeMessage);
	}
}
