package cz.microshop.webui.service.impl;

import cz.microshop.webui.model.Payment;
import cz.microshop.webui.service.PaymentService;
import cz.microshop.webui.service.client.PaymentRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRestClient paymentRestClient;

	@Override
	public void process(Payment payment) {

		paymentRestClient.process(payment);
	}
}
