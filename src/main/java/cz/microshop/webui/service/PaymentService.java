package cz.microshop.webui.service;

import cz.microshop.webui.model.Payment;

public interface PaymentService {
	void process(Payment payment);
}
