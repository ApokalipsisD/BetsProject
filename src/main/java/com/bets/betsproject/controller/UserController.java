package com.bets.betsproject.controller;

import com.bets.betsproject.exception.UserFoundException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<User> createUser(@RequestBody User user) throws UserFoundException {
        Optional<User> local = userService.getByLogin(user.getLogin());
        if(local.isPresent()){
            System.out.println("User is already here");
            throw new UserFoundException("User with this login is already exists in DB");
        }
        else {
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
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }



    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") Integer id,
                                           @RequestBody User user) {
//        System.out.println(id);
//        System.out.println(user);
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



//    @GetMapping("{id}")
//    public ResponseEntity<User> getUserByLogin(@PathVariable("id") Integer id) {
//        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
//    }

}
