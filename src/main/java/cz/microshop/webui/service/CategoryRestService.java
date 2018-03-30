package cz.microshop.webui.service;

import cz.microshop.webui.model.Category;
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
public class CategoryRestService {

    RestTemplate restTemplate = new RestTemplate();

    public List<Category> findAll()  {
        ResponseEntity<List<Category>> resp =
                restTemplate.exchange("http://localhost:8090/category/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
                        });
        List<Category> categories = resp.getBody();
        return categories;
    }
}
