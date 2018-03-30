package cz.microshop.webui.service;


import cz.microshop.webui.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

/*	@Autowired
	private ProductDao productDao;*/

	@Autowired
	private ProductRestService productRestService;

	@Override
	@Transactional
	public Product findById(Long id) {
		//return productDao.findOne(id);
		return productRestService.find(id);
	}

	@Override
	@Transactional
	public List<Product> getProductsByTerm(String searchTerm) {
		//return productDao.findByTerm(searchTerm);
		return productRestService.getProductsByTerm(searchTerm);
	}

	@Override
	@Transactional
	public List<Product> getProductsByCategoryId(Integer categoryId) {
		return productRestService.getProductsByCategoryId(categoryId);
		//return productDao.findByCategoryId((long)categoryId);
	}
	
	@Override
	@Transactional
	public List<Product> getSixLatestProducts() {
		return productRestService.getSixLatestProducts();
	}

	@Override
	public List<Product> getAll() {
		return productRestService.findAll();
	}
}
