package com.jtweet.gateway.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class GetUserByToken {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${jtweet.services.user.endpoint}")
    private String userEndpoint;

    public HashMap execute(String token) throws RestClientException {
        HttpEntity<String> request = createRequest(token);
        return restTemplate.postForObject(
                userEndpoint + "/v1/users/jwt",
                request,
                HashMap.class
        );
    }

    private HttpEntity<String> createRequest(String body) {
        return new HttpEntity<>(body);
    }
}
