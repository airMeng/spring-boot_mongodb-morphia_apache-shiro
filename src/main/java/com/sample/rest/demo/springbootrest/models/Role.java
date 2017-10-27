package com.sample.rest.demo.springbootrest.models;

import com.google.gson.Gson;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.Date;

@Entity(value = "roles", noClassnameStored = true)
public class Role {

    @Id
    private String code;
    private String name;
    private String description;
    private Date creationDate;
    private Date lastModifiedDate;

    public Role(){
        this.creationDate = new Date();
        this.lastModifiedDate = new Date();
    }
    public Role(String code){
        if(null!=code && 0 < code.trim().length()){
            this.code = code.toUpperCase();
            this.name = "ROLE_" + code.toUpperCase();
            this.creationDate = new Date();
            this.lastModifiedDate = new Date();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean validate(){
        return null!=code && null!=name && 0 < code.trim().length() && 0 < name.trim().length();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
