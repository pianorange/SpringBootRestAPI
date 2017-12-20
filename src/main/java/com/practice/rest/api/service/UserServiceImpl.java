package com.practice.rest.api.service;

import com.practice.rest.api.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteUserById(long id) {

    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }

    @Override
    public void deleteAllUsers() {

    }

    @Override
    public boolean isUserExist(User user) {
        return false;
    }
}
