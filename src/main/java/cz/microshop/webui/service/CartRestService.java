package cz.microshop.webui.service;

import cz.microshop.webui.model.Cart;
import cz.microshop.webui.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartRestService extends RestService {

    @Value("${url.restserviceurl.cart}")
    private String url;

    public List<Cart> findAll()  {
        ResponseEntity<List<Cart>> resp =
                getRestTemplate().exchange(url+"/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Cart>>() {
                        });
        List<Cart> products = resp.getBody();
        return products;
    }

    public Cart find(Long id)   {
        return getRestTemplate().getForObject(url+"/find?id="+id, Cart.class);
    }


    public Cart addItemToCart(Long cartId, Item item) {
        return getRestTemplate().postForObject(url+"/" + cartId + "/addItem", item, Cart.class);
    }

    public Cart save(Cart cart) {
        return getRestTemplate().postForObject(url+"/save", cart, Cart.class);
    }

    public void removeItem(Long cartId, Long itemId) {
        getRestTemplate().delete(url+"/" + cartId + "/removeItem?itemId="+itemId, HttpStatus.OK);
    }

    public Cart delete(Long id) {
        return getRestTemplate().getForObject(url+"/" + id + "/addItem", Cart.class);
    }

    public Cart clear(Long id) {
        return getRestTemplate().getForObject(url+"/" + id + "/clear", Cart.class);
    }

}
