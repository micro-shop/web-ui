package cz.microshop.webui.model;

import java.math.BigDecimal;

public class LineItem { //implements { //Comparable<LineItem> {

	private Long id;

	private Product product;

	private Cart cart;

	private Long productId;
	private BigDecimal unitPrice;

	private Long quantity;

	public LineItem(Long productId, BigDecimal unitPrice, Long quantity) {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

/*	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}*/

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	/*@Override
	public int compareTo(LineItem o) {
		
		return this.product.getId().compareTo(o.product.getId());
	}*/
}
