package cz.microshop.webui.service;

import cz.microshop.webui.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private EmailRestService emailRestService;

	@Override
	public void send(Email email) {
		emailRestService.send(email);
	}
}
