package cz.microshop.webui.service;

import cz.microshop.webui.model.Category;
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
public class CategoryRestService extends RestService {

    @Value("${url.restserviceurl.category}")
    private String url;

    public List<Category> findAll()  {
        ResponseEntity<List<Category>> resp =
                getRestTemplate().exchange(url+"/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Category>>() {
                        });
        List<Category> categories = resp.getBody();
        return categories;
    }
}
