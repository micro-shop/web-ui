package cz.microshop.webui.service;

import cz.microshop.webui.model.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {
/*
	@Autowired
	private ShippingDao shippingDao;*/

	@Autowired
	private ShippingRestService shippingRestService;
	
	@Override
	@Transactional
	public List<Shipping> findAllShippings() {
		return shippingRestService.findAll();
	}

	@Override
	@Transactional
	public Shipping findByName(String shippingName) {
		return shippingRestService.find(shippingName);
	}

}
