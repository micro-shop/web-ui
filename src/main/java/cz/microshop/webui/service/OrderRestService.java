package cz.microshop.webui.service;

import cz.microshop.webui.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderRestService extends RestService {

    @Value("${url.restserviceurl.orders}")
    private String url;

    public List<Order> findAll()  {
        ResponseEntity<List<Order>> resp =
                getRestTemplate().exchange(url+"/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                        });
        List<Order> products = resp.getBody();
        return products;
    }

    public Order find(Long id)   {
        return getRestTemplate().getForObject(url+"/find?id="+id, Order.class);
    }

    public Order save(Order order) {
        return getRestTemplate().postForObject(url+"/save", order, Order.class);
    }

    public List<Order> findByUserId(Long userId) {
        ResponseEntity<List<Order>> resp =
                getRestTemplate().exchange(url+"/findByUserId?id="+userId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                        });
        List<Order> orders = resp.getBody();
        return orders;
    }
}
