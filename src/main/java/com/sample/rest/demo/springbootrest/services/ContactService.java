package com.sample.rest.demo.springbootrest.services;

import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.User;
import org.bson.types.ObjectId;

import java.util.List;

public interface ContactService {

    List<Contact> list(String owner);
    Contact findById(ObjectId id);
    Contact findByFirstNameAndLastName(String firstName, String lastName);
    String add(Contact contact);
    String update(Contact contact);
    boolean remove(Contact contact);

}
