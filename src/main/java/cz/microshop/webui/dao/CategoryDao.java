package cz.microshop.webui.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.microshop.webui.model.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
