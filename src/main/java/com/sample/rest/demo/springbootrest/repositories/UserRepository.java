package com.sample.rest.demo.springbootrest.repositories;

import com.sample.rest.demo.springbootrest.models.User;

import java.util.List;

public interface UserRepository {

    User getUser(String username);
    boolean saveOrUpdateUser(User user);
    boolean updateUser(User user);
    boolean deleteUser(String username);
    List<User> listUsers(String search);

}
