package com.bets.betsproject.controller;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.User;
import com.bets.betsproject.service.api.BetService;
import com.bets.betsproject.service.api.RoleService;
import com.bets.betsproject.service.api.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final BetService betService;
    PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, BetService betService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.betService = betService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        Optional<User> local = userService.getByLogin(user.getLogin());
        if (local.isPresent()) {
            throw new ResourceNotFoundException("User is already exists");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setBalance(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP));
            user.setRole(roleService.getRoleById(1));
        }

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id,
                                           @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("id") Integer id) {

        betService.deleteBetsByUserId(id);

        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
