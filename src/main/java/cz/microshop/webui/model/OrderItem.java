package cz.microshop.webui.model;

import java.math.BigDecimal;

public class OrderItem {

	private Long id;

	private Long productId;
	private String productName;
	//@JsonIgnore
	//private Order order;
	private Long quantity;

	private BigDecimal unitPrice;
	
	public OrderItem() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}*/

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		//return "OrderItem [id=" + id + ", product=" + product + ", order=" + order + ", quantity=" + quantity + "]";
		return"";
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
