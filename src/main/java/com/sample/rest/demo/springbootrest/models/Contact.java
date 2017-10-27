package com.sample.rest.demo.springbootrest.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.Gson;
import com.sample.rest.demo.springbootrest.models.serializers.ObjectIdJsonSerializer;
import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Serialized;

import java.util.Date;
import java.util.List;

@Entity(value = "contacts", noClassnameStored = true)
public class Contact {

    @Id
    @JsonSerialize(using = ObjectIdJsonSerializer.class)
    private ObjectId id;

    private Details details;
    private ContactNumbers contactNumbers;
    private Socials socials;

    private String owner;
    private Date creationDate;
    private Date lastModifiedDate;

    private static Gson gson;


    public Contact() {
        gson = new Gson();
    }
    public Contact(String owner) {
        gson = new Gson();
        this.owner = owner;
    }
    public Contact(String firstName, String lastName) {
        Details details = new Details(firstName, lastName);
        this.details = details;
        gson = new Gson();
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public ContactNumbers getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(ContactNumbers contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Socials getSocials() {
        return socials;
    }

    public void setSocials(Socials socials) {
        this.socials = socials;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public boolean validate() {
        return (null != details && details.validate()) && (null!=contactNumbers && contactNumbers.validate() && (null!=owner && 0 < owner.trim().length()));
    }

    @Override
    public String toString() {
        return gson.toJson(this);
    }

    public static class Details {

        private String firstName;
        private String lastName;
        private String address;

        @Email
        private String email;

        public Details(){}
        public Details(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public Details(String firstName, String lastName, String email){
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }


        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean validate(){
            return null!=firstName && 0 < firstName.trim().length() && null!=lastName && 0 < lastName.trim().length();
        }

        @Override
        public String toString() {
            return gson.toJson(this);
        }
    }


    public static class ContactNumbers {

        private String mobileNo;
        private String telNo;
        private String homeNo;
        private String workNo;


        public ContactNumbers(){}
        public ContactNumbers(String mobileNo){
            this.mobileNo = mobileNo;
        }
        public ContactNumbers(String mobileNo, String telNo, String homeNo, String workNo){
            this.mobileNo = mobileNo;
            this.telNo = telNo;
            this.homeNo = homeNo;
            this.workNo = workNo;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getTelNo() {
            return telNo;
        }

        public void setTelNo(String telNo) {
            this.telNo = telNo;
        }

        public String getHomeNo() {
            return homeNo;
        }

        public void setHomeNo(String homeNo) {
            this.homeNo = homeNo;
        }

        public String getWorkNo() {
            return workNo;
        }

        public void setWorkNo(String workNo) {
            this.workNo = workNo;
        }


        public boolean validate(){
            return (null!=mobileNo && 0 < mobileNo.trim().length()) || (null!=telNo && 0 < telNo.trim().length()) || (null!=homeNo && 0 < homeNo.trim().length()) || (null!=workNo && 0 < workNo.trim().length());
        }

        @Override
        public String toString() {
            return gson.toJson(this);
        }
    }


    public static class Socials {

        private String facebook;
        private String google;
        private String twitter;
        private String instagram;

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getGoogle() {
            return google;
        }

        public void setGoogle(String google) {
            this.google = google;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getInstagram() {
            return instagram;
        }

        public void setInstagram(String instagram) {
            this.instagram = instagram;
        }

        @Override
        public String toString() {
            return gson.toJson(this);
        }
    }
}


