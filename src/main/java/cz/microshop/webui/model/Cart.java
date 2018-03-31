package cz.microshop.webui.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private Long cartId;
	private Long userId;
	private List<Item> items;
	
	public Cart() {
		items = new ArrayList<Item>();
	}

	public Cart(Long cartId, List<Item> items) {
		this.cartId = cartId;
		this.items = items;
	}

	@Override
	public String toString() {
		return "Cart [id=" + cartId + ", lineItems=" + items + "]";
	}
	
	public BigDecimal totalPrice() {
		double totalPrice = 0.0;
		for(Item lineItem : items) {
			totalPrice += (lineItem.getUnitPrice().doubleValue() * lineItem.getQuantity());
		}
		BigDecimal result = new BigDecimal(totalPrice);
		return result;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
