package cz.microshop.webui.service;

import java.math.BigDecimal;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Order;
import cz.microshop.webui.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import cz.microshop.webui.model.Shipping;

public interface OrderService {

	Order placeOrder(Cart cart, Shipping shipping, User user);

	BigDecimal totalOrderPriceWithShipping(Order order);

	Page<Order> userOrders(Long userId, Pageable pageable);
}
