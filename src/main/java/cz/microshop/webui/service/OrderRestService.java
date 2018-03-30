package cz.microshop.webui.service;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Item;
import cz.microshop.webui.model.Order;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderRestService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Order> findAll()  {
        ResponseEntity<List<Order>> resp =
                restTemplate.exchange("http://localhost:8090/product/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                        });
        List<Order> products = resp.getBody();
        return products;
    }

    public Order find(Long id)   {
        return restTemplate.getForObject("http://localhost:8090/order/find?id="+id, Order.class);
    }


    public Cart addItemToCart(Long cartId, Item item) {
        return restTemplate.postForObject("http://localhost:8090/cart/" + cartId + "/addItem", item, Cart.class);
    }

    public Order save(Order order) {
        return restTemplate.postForObject("http://localhost:8090/cart/save", order, Order.class);
    }

    public void removeItem(Long itemId) {
        restTemplate.delete("http://localhost:8090/cart/" + itemId + "/removeItem");
    }

    public Cart delete(Long id) {
        return restTemplate.getForObject("http://localhost:8090/cart/" + id + "/addItem", Cart.class);
    }

    public Cart clear(Long id) {
        return restTemplate.getForObject("http://localhost:8090/cart/" + id + "/clear", Cart.class);
    }

    public Page<Order> findByUserId(Long userId) {
/*        ResponseEntity<List<Order>> resp =
                restTemplate.exchange("http://localhost:8090/product/find?",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                        });
        List<Order> products = resp.getBody();
        return products;*/
        return null;
    }
}
