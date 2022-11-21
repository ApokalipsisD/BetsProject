package com.bets.betsproject.service.api;

import com.bets.betsproject.model.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Integer id);

    User updateUser(User user, Integer id);

    void deleteUser(Integer id);

//    User checkIfLoginFree(String login);

    User getByLogin(String login);

//    User updateUserBalance(Connection connection, User user);
}
