package com.sample.rest.demo.springbootrest.repositories;

import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.security.services.BCryptPasswordService;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private Datastore datastore;

    @Autowired
    private BCryptPasswordService bCryptPasswordService;


    @Override
    public User getUser(String username) {
        if(null!=username && 0 < username.trim().length())
            return datastore.get(User.class, username);
        else
            return null;
    }

    @Override
    public boolean saveOrUpdateUser(User user) {
        if(null!=user && user.validate()) {
            String encryptedPassword = bCryptPasswordService.encryptPassword(user.getPassword());
            user.setPassword(encryptedPassword);
            return null != datastore.save(user);
        }

        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if(null!=user && user.validate()) {
            User dbUser = this.getUser(user.getUsername());
            if(null!=dbUser){
                if(null!=user.getPassword()) {
                    String encryptedPassword = bCryptPasswordService.encryptPassword(user.getPassword());
                    dbUser.setPassword(encryptedPassword);
                }
                if(null!=user.getRoles())
                    dbUser.setRoles(user.getRoles());
                return null!=datastore.save(dbUser);
            }
        }
        return false;
    }

    @Override
    public boolean deleteUser(String username) {
        if(null!=username && 0 < username.trim().length()) {
            User dbUser = this.getUser(username);
            if(null!=dbUser) {
                return null != datastore.delete(dbUser);
            }
        }
        return false;
    }

    @Override
    public List<User> listUsers(String search) {
        Query<User> query = datastore.createQuery(User.class);
        if(null!=search && 0 < search.trim().length()) query.filter("username", search);
        query.order("username");
        return query.asList();
    }

}
