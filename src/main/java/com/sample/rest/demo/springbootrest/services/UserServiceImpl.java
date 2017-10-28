package com.sample.rest.demo.springbootrest.services;

import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> list(String search) {
        return this.userRepository.listUsers(search);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.getUser(username);
    }

    @Override
    public boolean save(User user) {
        return this.userRepository.saveOrUpdateUser(user);
    }

    @Override
    public boolean update(User user) {
        return this.userRepository.updateUser(user);
    }

    @Override
    public boolean remove(String username) {
        return this.userRepository.deleteUser(username);
    }

    @Override
    public boolean verifyPassword(String username, String currentPassword) {
        User user = this.userRepository.getUser(username);
        return BCrypt.checkpw(currentPassword, user.getPassword());
    }

}
