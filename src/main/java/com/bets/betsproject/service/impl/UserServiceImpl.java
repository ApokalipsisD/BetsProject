package com.bets.betsproject.service.impl;

import com.bets.betsproject.exception.ResourceNotFoundException;
import com.bets.betsproject.model.User;
import com.bets.betsproject.repository.UserRepository;
import com.bets.betsproject.service.api.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id)
        );
    }

    @Override
    public User updateUser(User user, Integer id) {
        User newUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        newUser.setLogin(user.getLogin());
        newUser.setPassword(user.getPassword());
        newUser.setName(user.getName());
        newUser.setSurname(user.getSurname());
        newUser.setAge(user.getAge());
        newUser.setEmail(user.getEmail());
        newUser.setBalance(user.getBalance());
        newUser.setRole(user.getRole());

        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepository.deleteById(id);
    }

//    @Override
//    public User checkIfLoginFree(String login) {
//        return userRepository.checkIfLoginFree(login);
//    }

    @Override
    public User getByLogin(String login) {
//        User user = userRepository.findByLogin(login);
//        if (Objects.isNull(user)) {
//            throw new ResourceNotFoundException("User", "Login", login);
//        }
//        return user;
        return null;
//        return userRepository.findByLogin(login).orElseThrow(() -> new ResourceNotFoundException("User", "Login", login));
    }

//    User user = userRepository.findByLogin(login);
//        if (Objects.isNull(user)) {
//        throw new ResourceNotFoundException("User", "Login", login);
//    }
//        return user;
}
