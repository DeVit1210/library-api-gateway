package com.example.apigateway;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {
    public String getTokenHeader() {
        return "Bearer ";
    }
    public String getAuthHeader() {
        return HttpHeaders.AUTHORIZATION;
    }

}
