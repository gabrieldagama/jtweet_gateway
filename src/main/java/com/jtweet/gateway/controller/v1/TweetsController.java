package com.jtweet.gateway.controller.v1;

import com.jtweet.gateway.api.CreateTweet;
import com.jtweet.gateway.api.GetUserByToken;
import com.jtweet.gateway.api.request.CreateTweetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;

@RestController
@RequestMapping("/v1/tweets")
public class TweetsController {

    @Value("${jtweet.services.tweet.endpoint}")
    private URI tweetEndpoint;

    @Value("${jtweet.services.user.endpoint}")
    private URI userEndpoint;

    @Autowired
    private GetUserByToken getUserByTokenApi;

    @Autowired
    private CreateTweet createTweetApi;

    @PostMapping
    public Mono<ResponseEntity<String>> createTweet(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
            @RequestBody CreateTweetRequest body
    ) throws Exception {
        HashMap userData = getUserByTokenApi.execute(authHeader.substring(7));
        String result = createTweetApi.execute(userData, body);
        return Mono.just(ResponseEntity.ok(result));
    }

    @GetMapping
    public Mono<ResponseEntity<byte[]>> tweetsList(ProxyExchange<byte[]> proxy) {
        return proxy.uri(tweetEndpoint.toString() + "/v1/tweets").get();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<byte[]>> tweet(ProxyExchange<byte[]> proxy) {
        return proxy.uri(tweetEndpoint.toString() + "/v1/tweets").get();
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<byte[]>> deleteTweet(ProxyExchange<byte[]> proxy) {
        return proxy.uri(tweetEndpoint.toString() + "/v1/tweets").delete();
    }
}
