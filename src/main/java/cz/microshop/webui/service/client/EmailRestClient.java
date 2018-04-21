package cz.microshop.webui.service.client;

import cz.microshop.webui.model.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailRestClient extends RestClient {

    @Value("${url.restserviceurl.email}")
    private String url;

    public Email send(Email email) {
        return getRestTemplate().postForObject(url+"/send", email, Email.class);
    }

}
