package cz.microshop.webui.service;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Item;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CartRestService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Cart> findAll()  {
        ResponseEntity<List<Cart>> resp =
                restTemplate.exchange("http://localhost:8090/product/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Cart>>() {
                        });
        List<Cart> products = resp.getBody();
        return products;
    }

    public Cart find(Long id)   {
        return restTemplate.getForObject("http://localhost:8090/product/find?id="+id, Cart.class);
    }


    public Cart addItemToCart(Long cartId, Item item) {
        return restTemplate.postForObject("http://localhost:8090/cart/" + cartId + "/addItem", item, Cart.class);
    }

    public Cart save(Cart cart) {
        return restTemplate.postForObject("http://localhost:8090/cart/save", cart, Cart.class);
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

}
