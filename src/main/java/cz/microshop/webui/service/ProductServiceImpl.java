package cz.microshop.webui.service;


import cz.microshop.webui.dao.ProductDao;
import cz.microshop.webui.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Override
	@Transactional
	public Product findById(Long id) {
		return productDao.findOne(id);
	}

	@Override
	@Transactional
	public Page<Product> getProductsByTerm(String searchTerm, Pageable pageable) {
		return productDao.findByTerm(searchTerm, pageable);
	}

	@Override
	@Transactional
	public Page<Product> getProductsByCategoryId(Integer categoryId, Pageable pageable) {
		return productDao.findByCategoryId((long)categoryId, pageable);
	}
	
	@Override
	@Transactional
	public Page<Product> getSixLatestProducts() {
		return productDao.findByTerm("", new PageRequest(0, 6));
	}

	@Override
	public List<Product> getAll() {
		return productDao.findAll();
	}
}
