package cz.microshop.webui.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private Long id;
	private BigDecimal total;
	
	/*@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="shipping_id")*/
	private Long shippingId;

	private Date createdAt;
	
	/*@ManyToOne
	@JoinColumn(name="user_id")*/
	private Long userId;

	private OrderStatus status;

	private List<OrderDetails> orderDetails;
	
	@Transient
	private BigDecimal totalWithShipping;
	
	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Shipping getShipping() {
		return null;
	}

	public void setShipping(Shipping shipping) {
		//this.shipping = shipping;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User2 getUser() {
		return null;
	}

	public void setUser(User2 user2) {
		//this.user2 = user2;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public BigDecimal getTotalWithShipping() {
		/*this.totalWithShipping = new BigDecimal(this.total.doubleValue() + this.shipping.getPrice().doubleValue());
		return this.totalWithShipping.setScale(2, RoundingMode.HALF_UP);*/
		return null;
	}

	public Order(BigDecimal total, Shipping shipping, Date createdAt, User user2, OrderStatus status) {
		this.orderDetails = new ArrayList<OrderDetails>();
		this.total = total;
		//this.shipping = shipping;
		this.createdAt = createdAt;
		//this.user2 = user2;
		this.status = status;
	}
	
	public Order() {
		this.orderDetails = new ArrayList<OrderDetails>();
	}

	@Override
	public String toString() {
		/*return "Order [id=" + id + ", total=" + total + ", shipping=" + shipping + ", createdAt=" + createdAt
				+ ", user=" + user + ", status=" + status + "]";*/
		return "";
	}
}
