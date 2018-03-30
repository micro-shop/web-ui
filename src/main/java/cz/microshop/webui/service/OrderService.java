package cz.microshop.webui.service;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Order;
import cz.microshop.webui.model.Shipping;
import cz.microshop.webui.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

	Order placeOrder(Cart cart, Shipping shipping, User user2);

	BigDecimal totalOrderPriceWithShipping(Order order);

	Page<Order> userOrders(Long userId, Pageable pageable);

	List<Order> userOrders(Long userId);
}
