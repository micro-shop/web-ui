package cz.microshop.webui.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {

    private final static Logger LOG = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
        LOG.debug("===========================request begin================================================");
        LOG.debug("URI         : "+ request.getURI());
        LOG.debug("Method      : "+ request.getMethod());
        LOG.debug("Headers     : "+ request.getHeaders() );
        LOG.debug("Request body: "+ new String(body, "UTF-8"));
        LOG.debug("==========================request end================================================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        LOG.debug("============================response begin==========================================");
        LOG.debug("Status code  : "+ response.getStatusCode());
        LOG.debug("Status text  : "+ response.getStatusText());
        LOG.debug("Headers      : "+ response.getHeaders());
        LOG.debug("Response body: "+ inputStringBuilder.toString());
        LOG.debug("=======================response end=================================================");
    }

}