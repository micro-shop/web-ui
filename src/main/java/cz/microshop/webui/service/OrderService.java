package cz.microshop.webui.service;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Order;
import cz.microshop.webui.model.Shipping;
import cz.microshop.webui.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

	Order placeOrder(Cart cart, Shipping shipping, User user2);

	BigDecimal totalOrderPriceWithShipping(Order order);

	List<Order> userOrders(Long userId);
}
