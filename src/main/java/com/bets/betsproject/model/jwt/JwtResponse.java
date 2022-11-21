package com.bets.betsproject.model.jwt;

import lombok.Data;

@Data
public class JwtResponse {
    private String login;
    private String password;
}