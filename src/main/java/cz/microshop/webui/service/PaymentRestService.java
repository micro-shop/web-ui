package cz.microshop.webui.service;

import cz.microshop.webui.model.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xnovm on 28.03.2018.
 */
@Service
public class PaymentRestService {

    RestTemplate restTemplate = new RestTemplate();

    public void process(Payment payment)   {
        restTemplate.postForObject("http://localhost:8097/payment/", payment, HttpStatus.class);
    }
}
