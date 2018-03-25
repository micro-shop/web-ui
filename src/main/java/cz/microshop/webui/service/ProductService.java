package cz.microshop.webui.service;


import cz.microshop.webui.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

	Product findById(Long id);

	Page<Product> getProductsByTerm(String searchTerm, Pageable pageable);

	Page<Product> getProductsByCategoryId(Integer page, Pageable pageable);

	Page<Product> getSixLatestProducts();

	List<Product> getAll();

}
