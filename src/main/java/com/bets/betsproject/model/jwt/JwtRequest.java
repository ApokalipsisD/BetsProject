package com.bets.betsproject.model.jwt;

import lombok.Data;

@Data
public class JwtRequest {
    private String token;
    public JwtRequest(String token) {
        this.token = token;
    }
}