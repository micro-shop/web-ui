package cz.microshop.webui.service;

import java.util.List;

import cz.microshop.webui.model.Shipping;

public interface ShippingService {
	List<Shipping> findAllShippings();
	Shipping findByName(String shippingName);
}
