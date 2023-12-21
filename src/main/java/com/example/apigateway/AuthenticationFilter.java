package com.example.apigateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    private final RouteValidator routeValidator;
    private final JwtUtils jwtUtils;
    public AuthenticationFilter(RouteValidator routeValidator, JwtUtils jwtUtils) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if(!routeValidator.isSecured.test(request)) {
                return chain.filter(exchange);
            }
            final String authenticationHeader = request.getHeaders().getFirst(jwtUtils.getAuthHeader());
            if(authenticationHeader == null || !authenticationHeader.startsWith(jwtUtils.getTokenHeader())) {
                throw new RuntimeException("missing authorization header");
            }
            String jwtToken = authenticationHeader.substring(jwtUtils.getTokenHeader().length());
            return chain.filter(exchange);
        };
    }

    public static class Config {

    }

}
