package cz.microshop.webui.service;

import cz.microshop.webui.model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * Created by xnovm on 28.03.2018.
 */
@Service
public class PaymentRestService extends RestService {

    @Value("${url.restserviceurl.payment}")
    private String url;

    public void process(Payment payment)   {
        getRestTemplate().postForObject(url+"/", payment, HttpStatus.class);
    }
}
