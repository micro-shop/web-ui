package cz.microshop.webui.controller;

import cz.microshop.webui.helpers.FlashMessage;
import cz.microshop.webui.model.*;
import cz.microshop.webui.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
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
	private PaymentService paymentService;
	@Autowired
	private EmailService emailService;

	@RequestMapping(value="/processPayment", method=RequestMethod.POST)
	public String paymentHook(Model model, @RequestParam("orderId") Long orderId) {
		System.out.println("PAYMENT SUCCESS FOR " + orderId);
		User user = userService.findByUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName());
		Payment payment = new Payment();
		payment.setOrderId(orderId);
		payment.setUserId(user.getUserId());
		paymentService.process(payment);
		return "thankyou";
	}

	@RequestMapping(value="/payment", method=RequestMethod.POST)
	public String payment(Model model, HttpServletRequest httpRequest, @RequestParam("orderId") Long orderId) {
		model.addAttribute("orderId", orderId);
		return "payment";
	}

	@RequestMapping(value="/checkout", method=RequestMethod.POST)
	public String checkout(Model model, HttpServletRequest httpRequest, @RequestParam("shipping") String shippingName) {
		Shipping shipping = shippingService.findByName(shippingName);
		Cart cart = cartService.findCart((Long)httpRequest.getSession().getAttribute("cart_id"));
		User user = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
		Order order = orderService.placeOrder(cart, shipping, user);
		FlashMessage.createFlashMessage("info", "Your order has been placed successfully.", model);
		emailService.send(constructEmailMessageWithOrderList(order, user));
		httpRequest.getSession().setAttribute("cartQuantity", cart.getItems().size());
		model.addAttribute("user", user);
		model.addAttribute("order", order);
		return "order_summary";
	}
	
	@RequestMapping(value = "/my_orders", method = RequestMethod.GET)
	public String showMyOrders(Model model, @RequestParam(name="page", defaultValue="0", required=false) Integer page) {
		User user = userService.findByUsername(SecurityContextHolder.getContext()
                .getAuthentication().getName());
		List<Order> orders = orderService.userOrders(user.getUserId());
		model.addAttribute("user", user);
		model.addAttribute("orders", orders);
		model.addAttribute("page", page);
		return "my_orders";
	}
	
	private Email constructEmailMessageWithOrderList(Order order, User user) {
		String content = FlashMessage.createOrderContentsMessage(order, user);
		Email email = new Email();
		email.setContent(content);
		email.setTo(user.getEmail());
		return email;
	}
}
