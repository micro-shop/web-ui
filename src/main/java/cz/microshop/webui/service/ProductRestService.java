package cz.microshop.webui.service;

import cz.microshop.webui.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRestService extends RestService {

    @Value("${url.restserviceurl.product}")
    private String url;

    public List<Product> findAll()  {
        ResponseEntity<List<Product>> resp =
                getRestTemplate().exchange(url+"/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = resp.getBody();
        return products;
    }

    public Product find(Long id)   {
        return getRestTemplate().getForObject("http://localhost:8090/product/find?id="+id, Product.class);
    }

    public List<Product> getProductsByTerm(String searchTerm)   {
        ResponseEntity<List<Product>> resp =
                getRestTemplate().exchange("http://localhost:8090/product/findByTerm?searchTerm="+searchTerm,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = resp.getBody();
        return products;
    }

    public List<Product> getProductsByCategoryId(Integer categoryId) {
        ResponseEntity<List<Product>> resp =
                getRestTemplate().exchange("http://localhost:8090/product/findByCategory?categoryId="+categoryId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = resp.getBody();
        return products;
    }

    public List<Product> getSixLatestProducts() {
        ResponseEntity<List<Product>> resp =
                getRestTemplate().exchange("http://localhost:8090/product/getSixBest",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = resp.getBody();
        return products;
    }
}
