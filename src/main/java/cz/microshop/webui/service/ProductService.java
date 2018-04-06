package cz.microshop.webui.service;


import cz.microshop.webui.model.Product;

import java.util.List;

public interface ProductService {

	Product findById(Long id);

	List<Product> getProductsByTerm(String searchTerm);

	List<Product> getProductsByCategoryId(Integer page);

	List<Product> getSixLatestProducts();

	List<Product> getAll();

}
