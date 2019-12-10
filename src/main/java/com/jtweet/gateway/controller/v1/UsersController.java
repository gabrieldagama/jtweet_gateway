package com.jtweet.gateway.controller.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashMap;

@RestController
@CrossOrigin
@RequestMapping("/v1/users")
public class UsersController {

    @Value("${jtweet.services.tweet.endpoint}")
    private URI tweetEndpoint;

    @Value("${jtweet.services.user.endpoint}")
    private URI userEndpoint;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<byte[]>> getUser(ProxyExchange<byte[]> proxy, @PathVariable Integer id) {
        return proxy.uri(userEndpoint.toString() + "/v1/users/"+ id).get();
    }

    @PostMapping
    public Mono<ResponseEntity<byte[]>> createUser(
            ProxyExchange<byte[]> proxy,
            @RequestBody HashMap<String,String> user
    ) {
        return proxy.uri(userEndpoint.toString() + "/v1/users").body(user).post();
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<byte[]>> login(ProxyExchange<byte[]> proxy) {
        return proxy.uri(userEndpoint.toString() + "/v1/users/login").post();
    }
}
