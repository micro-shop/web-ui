package cz.microshop.webui.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

	private Long id;
	private String name;
	private String description;

	private List<Product> products;
	
	public Category() {
		products = new ArrayList<Product>();
	}

	public Category(String name, String description, List<Product> products) {
		this.name = name;
		this.description = description;
		this.products = products;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
