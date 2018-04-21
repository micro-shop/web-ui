package cz.microshop.webui.service.impl;

import cz.microshop.webui.model.*;
import cz.microshop.webui.service.EmailService;
import cz.microshop.webui.service.client.EmailRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailRestClient emailRestClient;

	@Override
	public void send(Email email) {
		emailRestClient.send(email);
	}
}
