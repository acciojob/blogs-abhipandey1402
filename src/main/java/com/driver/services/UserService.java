package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BlogService blogService;

    public void createUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(int userId){
        userRepository.deleteById(userId);
    }

    public void updateUser(User user){

        userRepository.save(user);
    }

    public User findUserByUsername(String username){

        return userRepository.findByUsername(username);
    }
}