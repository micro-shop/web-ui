package cz.microshop.webui.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private Long orderId;
	private BigDecimal total;
	private Date createdAt;
	private Shipping shipping;
	private User user;

	private OrderStatus status;

	private List<OrderItem> orderItems;
	private BigDecimal totalWithShipping;

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public BigDecimal getTotalWithShipping() {
		this.totalWithShipping = new BigDecimal(this.total.doubleValue() + this.shipping.getPrice().doubleValue());
		return this.totalWithShipping.setScale(2, RoundingMode.HALF_UP);
	}

	public Order() {
		this.orderItems = new ArrayList<OrderItem>();
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Shipping getShipping() {
		return shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
