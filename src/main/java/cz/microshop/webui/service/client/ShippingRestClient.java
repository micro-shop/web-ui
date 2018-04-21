package cz.microshop.webui.service.client;

import cz.microshop.webui.model.Shipping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xnovm on 28.03.2018.
 */
@Service
public class ShippingRestClient extends RestClient {

    @Value("${url.restserviceurl.shipping}")
    private String url;

    public List<Shipping> findAll()  {
        ResponseEntity<List<Shipping>> shippingResponse =
                getRestTemplate().exchange(url+"/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Shipping>>() {
                        });
        List<Shipping> shippings = shippingResponse.getBody();
        return shippings;
    }

    public Shipping find(String name)   {
        return getRestTemplate().getForObject(url+"/find?name="+name, Shipping.class);
    }
}
