package cz.microshop.webui.service;

import cz.microshop.webui.helpers.LoggingRequestInterceptor;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class RestClient {

    private RestTemplate restTemplate;

    public RestClient() {
        restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        interceptors.add(new LoggingRequestInterceptor());
        restTemplate.setInterceptors(interceptors);
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

}
