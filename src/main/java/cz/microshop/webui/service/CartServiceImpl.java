package cz.microshop.webui.service;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Item;
import cz.microshop.webui.model.LineItem;
import cz.microshop.webui.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRestService cartRestService;
	@Autowired
	private ProductService productService;
	
	@Override
	public Cart addItemToCart(Long cartId, Long productId) {
		Product product = productService.findById(productId);
		Cart cart = cartId == null ? this.createCart() : cartRestService.find(cartId);
		if (product.getQuantity() <= 0L) {
			return cart;
		}
		Item i = new Item();
		i.setProductId(productId);
		i.setProductName(product.getName());
		i.setQuantity(1L);
		i.setUnitPrice(product.getPrice());

		return cartRestService.addItemToCart(cartId, i);
	}
	
	@Override
	public Cart createCart() {
		Cart cart = new Cart();
		return cartRestService.save(cart);
	}

	@Override
	public Cart findCart(Long cartId) {
		return cartRestService.find(cartId);
	}

	@Override
	public Cart updateProductQuantity(Long cartId, Long[] productIds, Long[] quantities) {
		
		int i = 0;
		Cart cart = cartRestService.find(cartId);
		for(Long productId : productIds) {
			Item item = cart.getItems().stream().filter(li -> li.getProductId().equals(productId)).findFirst().orElse(null);
			//LineItem lineItem = lineItemDao.findByProductIdAndCartId(productId, cartId).get();
			//Product product = lineItem.getProduct();
			//if((product.getQuantity() <= 0L) || (quantities[i] <= 0L)) {
			if((quantities[i] <= 0L)) {
				cart.getItems().remove(item);
				//lineItemDao.delete(lineItem);
			} else {
				item.setQuantity(quantities[i]);
				//updateLineItemQuantity(quantities[i], lineItem, product);
			}
			i++;
		}
		cartRestService.save(cart);
		return findCart(cartId);
	}

	@Override
	public void removeItemFromCart(Long cartId, Long itemId) {
		cartRestService.removeItem(cartId, itemId);
	}

	@Override
	public void destroyCart(Long cartId) {
		cartRestService.delete(cartId);
	}

	@Override
	public Cart save(Cart cart) {
		return cartRestService.save(cart);
	}

	@Override
	public Cart clearCart(Long cartId) {
		return cartRestService.clear(cartId);
	}
	
	private void updateLineItemQuantity(Long quantity, LineItem lineItem, Product product) {
		if(product.getQuantity() <= quantity) {
			lineItem.setQuantity(product.getQuantity());
		} else {
			lineItem.setQuantity(quantity);
		}
	}
}
