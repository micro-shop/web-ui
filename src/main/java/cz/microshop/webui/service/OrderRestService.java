package cz.microshop.webui.service;

import cz.microshop.webui.helpers.LoggingRequestInterceptor;
import cz.microshop.webui.model.Order;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRestService {

    RestTemplate restTemplate;

    public OrderRestService() {
        restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
    }

    public List<Order> findAll()  {
        ResponseEntity<List<Order>> resp =
                restTemplate.exchange("http://localhost:8093/product/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                        });
        List<Order> products = resp.getBody();
        return products;
    }

    public Order find(Long id)   {
        return restTemplate.getForObject("http://localhost:8093/order/find?id="+id, Order.class);
    }

    public Order save(Order order) {
        return restTemplate.postForObject("http://localhost:8093/order/save", order, Order.class);
    }

    public List<Order> findByUserId(Long userId) {
        ResponseEntity<List<Order>> resp =
                restTemplate.exchange("http://localhost:8093/order/findByUserId?id="+userId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Order>>() {
                        });
        List<Order> orders = resp.getBody();
        return orders;
    }
}
