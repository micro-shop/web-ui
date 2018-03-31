package cz.microshop.webui.model;

import java.math.BigDecimal;


public class Shipping {

	private Long shippingId;

	private String name;

	private BigDecimal price;
	
	public Shipping() {}
	
	public Shipping(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Shipping [name=" + name + ", price=" + price + "]";
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}
}
