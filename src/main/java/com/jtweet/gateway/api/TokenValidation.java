package com.jtweet.gateway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class TokenValidation {

    @Autowired
    private RestTemplate restTemplate;

    @Value("{$jtweet.services.user.endpoint}")
    private String userEndpoint;

    public HashMap validateToken(String token) throws RestClientException {
        HttpEntity<String> request = createRequestWithStringBody(token);
        return restTemplate.postForObject(
                userEndpoint+"/v1/users/validateJwt",
                request,
                HashMap.class
        );
    }

    private HttpEntity<String> createRequestWithStringBody(String body) {
        return new HttpEntity<>(body);
    }
}
