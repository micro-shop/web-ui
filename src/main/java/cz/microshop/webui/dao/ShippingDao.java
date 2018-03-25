package cz.microshop.webui.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.microshop.webui.model.Shipping;

public interface ShippingDao extends JpaRepository<Shipping, Long> {
	Shipping findByName(String shippingName);
}
