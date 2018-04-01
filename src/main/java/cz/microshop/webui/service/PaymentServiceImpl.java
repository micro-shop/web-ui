package cz.microshop.webui.service;

import cz.microshop.webui.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRestService paymentRestService;

	@Override
	public void process(Payment payment) {

		paymentRestService.process(payment);
	}
}
