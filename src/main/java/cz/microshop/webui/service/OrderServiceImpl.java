package cz.microshop.webui.service;

import cz.microshop.webui.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.Instant;

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
	@Transactional
	public Order placeOrder(Cart cart, Shipping shipping, User user2) {
		Order order = new Order(cart.totalPrice().setScale(2, RoundingMode.HALF_UP)
				, shipping, Date.from(Instant.now()),
				user2, OrderStatus.BEGIN);
		setOrderedProducts(cart, order);
		return orderRestService.save(order);
	}
	
	@Override
	@Transactional
	public BigDecimal totalOrderPriceWithShipping(Order order) {
		return new BigDecimal(order.getShipping().getPrice().doubleValue() + order.getTotal().doubleValue());
	}

	@Override
	public Page<Order> userOrders(Long userId, Pageable pageable) {
		return null;
	}

	@Transactional
	private void setOrderedProducts(Cart cart, Order order) {
		/*double totalPrice = 0.0;
		for(LineItem lineItem : cart.getLineItems()) {
			OrderDetails orderDetails = new OrderDetails();
			Product product = productRestService.find(lineItem.getProductId());
			
			if(product.getQuantity() <= 0L) {
				continue;
			}
			
			if(lineItem.getQuantity() >= product.getQuantity()) {
				orderDetails.setQuantity(product.getQuantity());
				product.setQuantity(0L);
			} else {
				orderDetails.setQuantity(lineItem.getQuantity());
				long diff = product.getQuantity() - lineItem.getQuantity();
				product.setQuantity(diff);
			}
			totalPrice += (product.getPrice().doubleValue() * orderDetails.getQuantity().doubleValue());
			orderDetails.setProduct(product);
			orderDetails.setOrder(order);
			lineItemDao.delete(lineItem);
			productRestService.save(product);
			orderDetailsDao.save(orderDetails);
			order.getOrderDetails().add(orderDetails);
		}
		cart.getLineItems().clear();
		cartRestService.save(cart);
		BigDecimal total = new BigDecimal(totalPrice);
		order.setTotal(total.setScale(2, RoundingMode.HALF_UP));*/
	}

	@Transactional
	@Override
	public Page<Order> userOrders(Long userId) {
		return orderRestService.findByUserId(userId);
	}
}
