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

//    User checkIfLoginFree(String login);

    Optional<User> getByLogin(String login);

//    User updateUserBalance(Connection connection, User user);
}
