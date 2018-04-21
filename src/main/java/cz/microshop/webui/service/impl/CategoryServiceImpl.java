package cz.microshop.webui.service.impl;

import cz.microshop.webui.model.Category;
import cz.microshop.webui.service.CategoryService;
import cz.microshop.webui.service.client.CategoryRestClient;
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
