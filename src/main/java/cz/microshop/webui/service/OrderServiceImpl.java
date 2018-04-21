package cz.microshop.webui.service;

import cz.microshop.webui.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private ProductRestClient productRestClient;

	@Autowired
	private CartRestClient cartRestClient;

	@Autowired
	private OrderRestClient orderRestClient;
	
	@Override
	public Order placeOrder(Cart cart, Shipping shipping, User user) {
		Order order = new Order();
		order.setCreatedAt(Date.from(Instant.now()));
		order.setStatus(OrderStatus.BEGIN);
		order.setShipping(shipping);
		order.setUser(user);
		setOrderedProducts(cart, order);
		return orderRestClient.save(order);
	}
	
	@Override
	public BigDecimal totalOrderPriceWithShipping(Order order) {
		return order.getTotalWithShipping();
	}

	private void setOrderedProducts(Cart cart, Order order) {
		double totalPrice = 0.0;
		for(Item cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			Product product = productRestClient.find(cartItem.getProductId());

			if(product.getQuantity() <= 0L) {
				continue;
			}
			
			if(cartItem.getQuantity() >= product.getQuantity()) {
				orderItem.setQuantity(product.getQuantity());
				product.setQuantity(0L);
			} else {
				orderItem.setQuantity(cartItem.getQuantity());
				long diff = product.getQuantity() - cartItem.getQuantity();
				product.setQuantity(diff);
			}
			totalPrice += (product.getPrice().doubleValue() * orderItem.getQuantity().doubleValue());
			orderItem.setProductId(cartItem.getProductId());
			orderItem.setProductName(cartItem.getProductName());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setUnitPrice(cartItem.getUnitPrice());
			order.getOrderItems().add(orderItem);
		}
		cart.getItems().clear();
		cartRestClient.save(cart);
		BigDecimal total = new BigDecimal(totalPrice);
		order.setTotal(total.setScale(2, RoundingMode.HALF_UP));
	}

	@Override
	public List<Order> userOrders(Long userId) {
		return orderRestClient.findByUserId(userId);
	}
}
