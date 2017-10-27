package com.sample.rest.demo.springbootrest.services;

import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.ContactRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<Contact> list(String owner) {
        return this.contactRepository.listContact(owner);
    }

    @Override
    public Contact findById(ObjectId id) {
        return this.contactRepository.getContactById(id);
    }

    @Override
    public Contact findByFirstNameAndLastName(String firstName, String lastName) {
        return this.contactRepository.getContact(firstName, lastName);
    }

    @Override
    public String add(Contact contact) {
        return this.contactRepository.saveOrUpdateContact(contact);
    }

    @Override
    public String update(Contact contact) {
        return this.contactRepository.updateContact(contact);
    }

    @Override
    public boolean remove(Contact contact) {
        return this.contactRepository.deleteContact(contact);
    }
}
