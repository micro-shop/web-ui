package cz.microshop.webui.dao;

import cz.microshop.webui.model.Shipping;

public interface ShippingDao { //extends JpaRepository<Shipping, Long> {
	Shipping findByName(String shippingName);
}
