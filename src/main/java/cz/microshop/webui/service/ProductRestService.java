package cz.microshop.webui.service;

import cz.microshop.webui.model.CustomPageImpl;
import cz.microshop.webui.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRestService extends RestService {

    @Value("${url.restserviceurl.product}")
    private String url;

    public Page<Product> findAll(Pageable pageable)  {
        ResponseEntity<CustomPageImpl<Product>> resp =
                getRestTemplate().exchange(url+"/findAll",
                        HttpMethod.GET, null, new ParameterizedTypeReference<CustomPageImpl<Product>>()  {
                        });
        CustomPageImpl<Product> products = resp.getBody();
        return products;
    }

    public Product find(Long id)   {
        return getRestTemplate().getForObject(url+"/find?id="+id, Product.class);
    }

    public Page<Product> getProductsByTerm(String searchTerm)   {
        ResponseEntity<CustomPageImpl<Product>> resp =
                getRestTemplate().exchange(url+"/findByTerm?searchTerm="+searchTerm,
                        HttpMethod.GET, null, new ParameterizedTypeReference<CustomPageImpl<Product>>()  {
                        });
        CustomPageImpl<Product> products = resp.getBody();
        return products;
    }

    public Page<Product> getProductsByCategoryId(Integer categoryId) {
        ResponseEntity<CustomPageImpl<Product>> resp =
                getRestTemplate().exchange(url+"/findByCategory?categoryId="+categoryId,
                        HttpMethod.GET, null, new ParameterizedTypeReference<CustomPageImpl<Product>>()  {
                        });
        CustomPageImpl<Product> products = resp.getBody();
        return products;
    }

    public List<Product> getSixLatestProducts() {
        ResponseEntity<List<Product>> resp =
                getRestTemplate().exchange(url+"/getSixBest",
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = resp.getBody();
        return products;
    }
}
