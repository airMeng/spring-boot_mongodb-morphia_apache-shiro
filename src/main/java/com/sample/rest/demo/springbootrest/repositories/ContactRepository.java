package com.sample.rest.demo.springbootrest.repositories;

import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface ContactRepository {

    String saveOrUpdateContact(Contact contact);
    Contact getContactById(ObjectId id);
    Contact getContact(String firstName, String lastName);
    String updateContact(Contact contact);
    boolean deleteContact(Contact contact);
    List<Contact> listContact(String owner);

}
