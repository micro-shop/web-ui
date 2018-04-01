package cz.microshop.webui.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private Long orderId;
	private BigDecimal total;
	private Long shippingId;
	private Date createdAt;
	private Long userId;

	private OrderStatus status;

	private List<OrderItem> orderItems;

	//@Transient
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
		/*this.totalWithShipping = new BigDecimal(this.total.doubleValue() + this.shipping.getPrice().doubleValue());
		return this.totalWithShipping.setScale(2, RoundingMode.HALF_UP);*/
		return null;
	}

	public Order() {
		this.orderItems = new ArrayList<OrderItem>();
	}

	@Override
	public String toString() {
		/*return "Order [id=" + id + ", total=" + total + ", shipping=" + shipping + ", createdAt=" + createdAt
				+ ", user=" + user + ", status=" + status + "]";*/
		return "";
	}

	public Long getShippingId() {
		return shippingId;
	}

	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
}
