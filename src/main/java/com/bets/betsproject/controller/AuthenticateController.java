package com.bets.betsproject.controller;

import com.bets.betsproject.config.CustomUserDetailsService;
import com.bets.betsproject.config.JWTGenerator;
import com.bets.betsproject.model.User;
import com.bets.betsproject.model.jwt.JwtRequest;
import com.bets.betsproject.model.jwt.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticateController {
    private final CustomUserDetailsService customUserDetailsService;
    private AuthenticationManager authenticationManager;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthenticateController(AuthenticationManager authenticationManager, JWTGenerator jwtGenerator,
                                  CustomUserDetailsService customUserDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("login")
    public ResponseEntity<JwtRequest> login(@RequestBody(required = false) JwtResponse jwtResponse) {
        Authentication authentication;
        authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtResponse.getLogin(),
                        jwtResponse.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new JwtRequest(token), HttpStatus.OK);
    }

    @GetMapping("current-user")
    public User getCurrentUser(Principal principal) {
        return (User) this.customUserDetailsService.loadUserByUsername(principal.getName());
    }
}