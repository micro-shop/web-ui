package cz.microshop.webui.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.microshop.webui.dao.CategoryDao;
import cz.microshop.webui.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	@Transactional
	public List<Category> getCategories() {
		return categoryDao.findAll();
	}
	
}
