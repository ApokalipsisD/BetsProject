package com.bets.betsproject.controller;

import com.bets.betsproject.model.User;
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
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    private final UserService userService;

    //todo change to service
    private final RoleService roleService;

    PasswordEncoder passwordEncoder;

    public UserController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBalance(BigDecimal.valueOf(0).setScale(2, RoundingMode.HALF_UP));
        user.setRole(roleService.getRoleById(1));
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }


    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }



    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }



    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id,
                                           @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>("User deleted successfully!", HttpStatus.OK);
    }



//    @GetMapping("{id}")
//    public ResponseEntity<User> getUserByLogin(@PathVariable("id") Integer id) {
//        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
//    }

}
