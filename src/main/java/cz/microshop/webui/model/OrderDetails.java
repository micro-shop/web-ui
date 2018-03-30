package cz.microshop.webui.model;

public class OrderDetails {

	private Long id;
	
	/*@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="product_id")*/
	private Long productId;
	
	/*@ManyToOne
	@JoinColumn(name="order_id")*/
	private Order order;
	private Long quantity;
	
	public OrderDetails() {}

	public OrderDetails(Product product, Order order, Long quantity) {
		super();
		//this.product = product;
		this.order = order;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return null;
	}

	public void setProduct(Product product) {
		//this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		//return "OrderDetails [id=" + id + ", product=" + product + ", order=" + order + ", quantity=" + quantity + "]";
		return"";
	}
}
