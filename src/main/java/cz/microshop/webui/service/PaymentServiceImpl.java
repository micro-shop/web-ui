package cz.microshop.webui.service;

import cz.microshop.webui.model.Payment;
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
