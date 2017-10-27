package com.sample.rest.demo.springbootrest.models;

import com.google.gson.Gson;

public class UserBean {

    private String username;
    private String password;
    private String newPassword;
    private String verifyPassword;


    public UserBean(){}
    public UserBean(String username, String password, String verifyPassword){
        this.username = username;
        this.password = password;
        this.verifyPassword = verifyPassword;
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

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public boolean validate(){
        return null!=username && 0 < username.trim().length() && null!=password && 0 < password.trim().length() && null!=newPassword && 0 < newPassword.trim().length() && null!=verifyPassword && 0 < verifyPassword.trim().length();
    }

    public boolean confirmPasswords(){
        return null!=newPassword && null!=verifyPassword && newPassword.equals(verifyPassword);
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
