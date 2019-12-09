package com.jtweet.gateway.api;

import com.jtweet.gateway.api.request.CreateTweetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class CreateTweet {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${jtweet.services.tweet.endpoint}")
    private String tweetEndpoint;

    public String execute(HashMap userData, CreateTweetRequest requestBody) throws RestClientException {
        requestBody.addTweetUserIdAndUsername(
                userData.get("id").toString(),
                userData.get("username").toString()
        );
        return restTemplate.postForObject(
                tweetEndpoint + "/v1/tweets",
                createRequest(requestBody),
                String.class
        );
    }

    private HttpEntity<CreateTweetRequest> createRequest(CreateTweetRequest request) {
            return new HttpEntity<CreateTweetRequest>(request);
    }

}
