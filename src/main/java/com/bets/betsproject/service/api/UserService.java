package com.bets.betsproject.service.api;

import com.bets.betsproject.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);

    User updateUser(User user, Integer id);

    void deleteUser(Integer id);

    Optional<User> getByLogin(String login);

}
