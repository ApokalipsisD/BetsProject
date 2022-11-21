package com.bets.betsproject.controller;

//@RestController
//public class AuthenticateController {
//
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @PostMapping("/generate-token")
//    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
//        try {
//            authenticate(jwtRequest.getLogin(), jwtRequest.getPassword());
//        } catch (ResourceNotFoundException e){
//            e.printStackTrace();
//            throw new Exception("User not found ");
//        }
//
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getLogin());
//        String token = this.jwtUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new JwtResponse(token));
//    }
//
//
//
//    public void authenticate(String login, String password) throws Exception {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
//        } catch (DisabledException e) {
//            throw new Exception("USER DISABLED " + e.getMessage());
//        } catch (BadCredentialsException e) {
//            throw new Exception("Invalid Credentials " + e.getMessage());
//        }
//    }
//}

import com.bets.betsproject.config.CustomUserDetailsService;
import com.bets.betsproject.config.JWTGenerator;
import com.bets.betsproject.exception.ResourceNotFoundException;
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

    //    @RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
    @PostMapping("login")
    public ResponseEntity<JwtRequest> login(@RequestBody(required = false) JwtResponse jwtResponse) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtResponse.getLogin(),
                            jwtResponse.getPassword()));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new JwtRequest(token), HttpStatus.OK);
    }

    @GetMapping("current-user")
    public User getCurrentUser(Principal principal) {
        System.out.println(principal.getName());
        return (User) this.customUserDetailsService.loadUserByUsername(principal.getName());
    }
//    @GetMapping("current-user")
//    public ResponseEntity<User> getCurrentUser(Principal principal) {
//        System.out.println(principal.getName());
//
//        return new ResponseEntity<>(userRepository.findByLogin(principal.getName())
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found")), HttpStatus.OK);
//    }

}

//    @PostMapping("register")
//    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
//        if (userRepository.existsByUsername(registerDto.getUsername())) {
//            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
//        }
//
//        UserEntity user = new UserEntity();
//        user.setUsername(registerDto.getUsername());
//        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
//
//        Role roles = roleRepository.findByName("USER").get();
//        user.setRoles(Collections.singletonList(roles));
//
//        userRepository.save(user);
//
//        return new ResponseEntity<>("User registered success!", HttpStatus.OK);
//    }
//}
