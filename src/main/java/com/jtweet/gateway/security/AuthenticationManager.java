package com.jtweet.gateway.security;

import com.jtweet.gateway.api.GetUserByToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    @Autowired
    private GetUserByToken getUserByTokenApi;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        try {
            HashMap userHashMap = getUserByTokenApi.execute(authToken);
            List<GrantedAuthority> roles = new ArrayList<>();
            roles.add(new SimpleGrantedAuthority(userHashMap.get("role").toString()));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    userHashMap.get("username"),
                    null,
                    roles
            );
            return Mono.just(auth);
        } catch (RestClientException exception) {
            return Mono.empty();
        }
    }
}