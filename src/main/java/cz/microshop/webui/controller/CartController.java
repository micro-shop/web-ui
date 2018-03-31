package cz.microshop.webui.controller;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Shipping;
import cz.microshop.webui.service.CartService;
import cz.microshop.webui.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ShippingService shippingService;
	
	@RequestMapping(value="show", method=RequestMethod.GET)
	public String showCart(Model model, HttpServletRequest httpRequest) {
		Long cartIdInSession = findCartIdInSession(httpRequest);
		if (cartIdInSession == null) {
			return "shoppingCart";
		}
		Cart cart = cartService.findCart(cartIdInSession);
		if(cart == null || (cart.getItems().size() <= 0L)) {
			return "redirect:/";
		}
		model.addAttribute("cart", cart);
		model.addAttribute("totalPrice", cart.totalPrice().setScale(2, RoundingMode.HALF_UP));
		return "shoppingCart";
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String updateCart(Model model, HttpServletRequest httpRequest, @RequestParam("product_ids[]") String[] productIdsStr,
			@RequestParam("quantity[]") String[] quantityStr) {
		Long[] productIds = Arrays.stream(productIdsStr).map(Long::parseLong).toArray(Long[]::new);
		Long[] quantity = Arrays.stream(quantityStr).map(Long::parseLong).toArray(Long[]::new);
		Long cartId = findCartIdInSession(httpRequest);
		if(checkIfUpdateCartParamsAreEmptyOrNull(productIds, quantity) || (cartId == null)) {
			return "redirect:/cart/show";
		}
		Cart cart = cartService.updateProductQuantity(cartId, productIds, quantity);
		
		httpRequest.getSession().setAttribute("cartQuantity", cart.getItems().size());
		model.addAttribute("cart", cart);
		model.addAttribute("totalPrice", cart.totalPrice().setScale(2, RoundingMode.HALF_UP));
		return "shoppingCart";
	}

	@RequestMapping(value="removeItem/{id}", method=RequestMethod.GET)
	public String removeItemFromCart(Model model, HttpServletRequest httpRequest, @PathVariable("id") Long itemId) {
		Cart cart = cartService.findCart(findCartIdInSession(httpRequest));
		if((cart != null) && (itemId != null)) {
			cartService.removeItemFromCart(cart.getCartId(), itemId);
			httpRequest.getSession().setAttribute("cartQuantity", cart.getItems().size());
		}		
		return "redirect:/cart/show";
	}
	
	@RequestMapping(value="clearCart", method=RequestMethod.GET)
	public String clearCart(Model model, HttpServletRequest httpRequest) {
		Long cartId = findCartIdInSession(httpRequest);
		if(cartId != null) {
			cartService.clearCart(cartId);
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/add_to_cart", method = RequestMethod.POST)
	public String addToCart(@RequestParam("product_id") String productId, Model model, HttpServletRequest httpRequest) {
		Long id = Long.parseLong(productId);
		Cart cart = null;
		if(httpRequest.getSession().getAttribute("cart_id") == null) {
			cart = cartService.createCart();
			httpRequest.getSession().setAttribute("cart_id", cart.getCartId());
		} else {
			cart = cartService.findCart(findCartIdInSession(httpRequest));
		}

		if(id != null) {
			cart = cartService.addItemToCart(cart.getCartId(), id);
		}
		httpRequest.getSession().setAttribute("cartQuantity", cart.getItems().size());
		return "redirect:/cart/show";
	}
	
	@RequestMapping(value="/preorder", method=RequestMethod.GET)
	public String preOrder(HttpServletRequest httpRequest, Model model) {
		Cart cart = cartService.findCart(findCartIdInSession(httpRequest));
		
		List<Shipping> shippingOptions = shippingService.findAllShippings();
		
		model.addAttribute("cart", cart);
		model.addAttribute("shipping", shippingOptions);
		model.addAttribute("totalPrice", cart.totalPrice().setScale(2, RoundingMode.HALF_UP));
		return "preorder";
	}
	
	private Long findCartIdInSession(HttpServletRequest httpRequest) {
		return (Long)httpRequest.getSession().getAttribute("cart_id");
	}
	
	private boolean checkIfUpdateCartParamsAreEmptyOrNull(Long[] productIds, Long[] quantity) {
		return checkIfProductIdsIsNullOrEmpty(productIds) || 
				checkIfProductQuantityIsNullOrEmpty(quantity);
	}
	
	private boolean checkIfProductIdsIsNullOrEmpty(Long[] productIds) {
		return productIds == null || (productIds.length == 0);
	}
	
	private boolean checkIfProductQuantityIsNullOrEmpty(Long[] quantity) {
		return quantity == null || (quantity.length == 0);
	}
}
