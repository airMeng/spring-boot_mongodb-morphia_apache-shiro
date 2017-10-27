package com.sample.rest.demo.springbootrest.models;

import com.google.gson.Gson;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(value = "users", noClassnameStored = true)
public class User {

    @Id
    private String username;
    private String password;

    @Reference
    private List<Role> roles;

    private Date creationDate;
    private Date lastModifiedDate;

    public User(){
        this.creationDate = new Date();
        this.lastModifiedDate = new Date();
    }
    public User(String username){
        if(null!=username && 0 < username.trim().length()) {
            this.username = username;
            this.creationDate = new Date();
            this.lastModifiedDate = new Date();
        }
    }
    public User(String username, String password){
        if(null!=username && 0 < username.trim().length() && null!=password && 0 < password.trim().length()) {
            this.username = username;
            this.password = password;
            this.creationDate = new Date();
            this.lastModifiedDate = new Date();
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        if(null==roles)
            roles = new ArrayList<>();

        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

//    public List<GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> authList = new ArrayList<>();
//        if(null!=roles){
//            for(Role r : roles){
//                authList.add(new SimpleGrantedAuthority(r.getCode().toUpperCase()));
//            }
//        }
//        return authList;
//    }

    public boolean validate(){
        return null!=username && null!=password && 0 < username.trim().length() && 0 < password.trim().length() && null!=roles && 0 < roles.size();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
