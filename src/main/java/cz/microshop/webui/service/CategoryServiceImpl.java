package cz.microshop.webui.service;

import cz.microshop.webui.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRestClient categoryRestClient;

	@Override
	public List<Category> getCategories() {
		return categoryRestClient.findAll();
	}
	
}
