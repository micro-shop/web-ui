package cz.microshop.webui.service;

import cz.microshop.webui.model.Shipping;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by xnovm on 28.03.2018.
 */
@Service
public class ShippingRestService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Shipping> findAll()  {
        ResponseEntity<List<Shipping>> shippingResponse =
                restTemplate.exchange("http://localhost:8090/user/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Shipping>>() {
                        });
        List<Shipping> shippings = shippingResponse.getBody();
        return shippings;
    }

    public Shipping find(String name)   {
        return restTemplate.getForObject("http://localhost:8090/user/find?name="+name, Shipping.class);
    }
}
