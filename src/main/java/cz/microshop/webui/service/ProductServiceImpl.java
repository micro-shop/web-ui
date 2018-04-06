package cz.microshop.webui.service;


import cz.microshop.webui.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRestService productRestService;

	@Override
	public Product findById(Long id) {
		return productRestService.find(id);
	}

	@Override
	public List<Product> getProductsByTerm(String searchTerm) {
		return productRestService.getProductsByTerm(searchTerm);
	}

	@Override
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productRestService.getProductsByCategoryId(categoryId);
	}
	
	@Override
	public List<Product> getSixLatestProducts() {
		return productRestService.getSixLatestProducts();
	}

	@Override
	public Page<Product> getAll(Pageable pageable) {
		return productRestService.findAll(pageable);
	}
}
