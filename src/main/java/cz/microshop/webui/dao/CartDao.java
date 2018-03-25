package cz.microshop.webui.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.microshop.webui.model.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {

}
