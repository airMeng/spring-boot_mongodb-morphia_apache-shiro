package com.sample.rest.demo.springbootrest.services;

import com.sample.rest.demo.springbootrest.models.User;

import java.util.List;

public interface UserService {

    List<User> list(String search);
    User findByUsername(String username);
    boolean save(User user);
    boolean update(User user);
    boolean remove(String username);
    boolean verifyPassword(String username, String currentPassword);
}
