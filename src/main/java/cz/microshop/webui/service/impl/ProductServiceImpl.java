package cz.microshop.webui.service.impl;


import cz.microshop.webui.model.Product;
import cz.microshop.webui.service.ProductService;
import cz.microshop.webui.service.client.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRestClient productRestClient;

	@Override
	public Product findById(Long id) {
		return productRestClient.find(id);
	}

	@Override
	public List<Product> getProductsByTerm(String searchTerm) {
		return productRestClient.getProductsByTerm(searchTerm);
	}

	@Override
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productRestClient.getProductsByCategoryId(categoryId);
	}
	
	@Override
	public List<Product> getSixLatestProducts() {
		return productRestClient.getSixLatestProducts();
	}

	@Override
	public List<Product> getAll() {
		return productRestClient.findAll();
	}
}
