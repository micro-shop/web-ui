package cz.microshop.webui.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.microshop.webui.dao.ShippingDao;
import cz.microshop.webui.model.Shipping;

@Service
public class ShippingServiceImpl implements ShippingService {

	@Autowired
	private ShippingDao shippingDao;
	
	@Override
	@Transactional
	public List<Shipping> findAllShippings() {
		return shippingDao.findAll();
	}

	@Override
	@Transactional
	public Shipping findByName(String shippingName) {
		return shippingDao.findByName(shippingName);
	}

}
