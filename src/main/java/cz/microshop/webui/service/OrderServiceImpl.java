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
/*
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderDetailsDao orderDetailsDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private LineItemDao lineItemDao;
	
	@Autowired
	private CartDao cartDao;*/

	@Autowired
	private ProductRestService productRestService;

	@Autowired
	private CartRestService cartRestService;

	@Autowired
	private OrderRestService orderRestService;
	
	@Override
	//@Transactional
	public Order placeOrder(Cart cart, Shipping shipping, User user) {
		Order order = new Order();
		order.setCreatedAt(Date.from(Instant.now()));
		order.setStatus(OrderStatus.BEGIN);
		order.setShippingId(shipping.getShippingId());
		order.setUserId(user.getUserId());

		/*Order order = new Order(cart.totalPrice().setScale(2, RoundingMode.HALF_UP)
				, shipping, Date.from(Instant.now()),
				user2, OrderStatus.BEGIN);*/
		setOrderedProducts(cart, order);
		return orderRestService.save(order);
	}
	
	@Override
	//@Transactional
	public BigDecimal totalOrderPriceWithShipping(Order order) {
		//return new BigDecimal(order.getgetShipping().getPrice().doubleValue() + order.getTotal().doubleValue());
		return order.getTotalWithShipping();
	}

	//@Transactional
	private void setOrderedProducts(Cart cart, Order order) {
		double totalPrice = 0.0;
		for(Item cartItem : cart.getItems()) {
			OrderItem orderItem = new OrderItem();
			Product product = productRestService.find(cartItem.getProductId());

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
			//orderItem.setOrder(order);
			//lineItemDao.delete(cartItem);
			//productRestService.save(product);
			//orderDetailsDao.save(orderDetails);
			order.getOrderItems().add(orderItem);
		}
		cart.getItems().clear();
		cartRestService.save(cart);
		BigDecimal total = new BigDecimal(totalPrice);
		order.setTotal(total.setScale(2, RoundingMode.HALF_UP));
	}

	//@Transactional
	@Override
	public List<Order> userOrders(Long userId) {
		return orderRestService.findByUserId(userId);
	}
}
