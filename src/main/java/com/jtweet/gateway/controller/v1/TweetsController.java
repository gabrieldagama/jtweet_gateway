package com.jtweet.gateway.controller.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/v1/tweets")
public class TweetsController {

    @Value("${jtweet.services.tweet.endpoint}")
    private URI tweetEndpoint;

    @Value("${jtweet.services.user.endpoint}")
    private URI userEndpoint;

    @PostMapping
    public Mono<ResponseEntity<byte[]>> createTweet(ProxyExchange<byte[]> proxy) throws Exception {
        return proxy.uri(tweetEndpoint.toString() + "/v1/tweets").post();
    }

    @GetMapping
    public Mono<ResponseEntity<byte[]>> tweetsList(ProxyExchange<byte[]> proxy) throws Exception {
        return proxy.uri(tweetEndpoint.toString() + "/v1/tweets").get();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<byte[]>> tweet(ProxyExchange<byte[]> proxy) throws Exception {
        return proxy.uri(tweetEndpoint.toString() + "/v1/tweets").get();
    }
}
