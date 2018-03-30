package cz.microshop.webui.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

	private Long id;

	private List<LineItem> lineItems;
	
	public Cart() {
		lineItems = new ArrayList<LineItem>();
	}

	public Cart(Long id, List<LineItem> lineItems) {
		this.id = id;
		this.lineItems = lineItems;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", lineItems=" + lineItems + "]";
	}
	
	public BigDecimal totalPrice() {
		double totalPrice = 0.0;
		for(LineItem lineItem : lineItems) {
			//totalPrice += (lineItem.getProduct().getPrice().doubleValue() * lineItem.getQuantity());
		}
		BigDecimal result = new BigDecimal(totalPrice);
		return result;
	}
}
