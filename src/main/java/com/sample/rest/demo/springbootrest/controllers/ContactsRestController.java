package com.sample.rest.demo.springbootrest.controllers;

import com.google.gson.Gson;
import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.MessageState;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.ContactRepository;
import com.sample.rest.demo.springbootrest.services.ContactService;
import com.sample.rest.demo.springbootrest.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000", "https://borgymanotoy.auth0.com"})
public class ContactsRestController {

    @Autowired
    private UserService userService;


    @Autowired
    private ContactService contactService;


    @GetMapping(value = "/")
    public ResponseEntity<?> greetings() {
        String greetings = "Hello Davao City, Philippines";
        return new ResponseEntity<Object>(greetings, HttpStatus.OK);
    }

    @GetMapping(value = "/listContact")
    public ResponseEntity<?> listContact(@RequestParam(value = "owner", required = false) String owner){
        List<Contact> contacts = this.contactService.list(owner);
        return new ResponseEntity<Object>(contacts, HttpStatus.OK);
    }

    @PostMapping(value = "/addContact")
    public ResponseEntity<?> addContact(@RequestBody Contact contact) {
        MessageState msgState = new MessageState("ERROR", "Unable to add contact!");
        if(null!= contact){
            contact.setCreationDate(new Date());
            contact.setLastModifiedDate(new Date());
            String id = this.contactService.add(contact);
            msgState.setCode("OK");
            msgState.setMessage("Successefully added contact!");
            if(null!=id) msgState.setResultObjectId(id);
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }

    @GetMapping(value = "/getContact")
    public ResponseEntity<?> getContact(@RequestParam(value = "id") ObjectId id){
        Contact contact = this.contactService.findById(id);
        return new ResponseEntity<Object>(contact, HttpStatus.OK);
    }

    @PostMapping(value = "/updateContact")
    public ResponseEntity<?> updateContact(@RequestBody Contact contact){
        Gson gson = new Gson();
        System.out.println("\n[update-contact]: " + gson.toJson(contact));


        MessageState msgState = new MessageState("ERROR", "Unable to update contact!");
        if(null!= contact){
            String id = this.contactService.update(contact);
            msgState.setCode("OK");
            msgState.setMessage("Successfully updated contact!");
            if(null!=id) msgState.setResultObjectId(id);
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }

    @PostMapping(value = "/removeContact")
    public ResponseEntity<?> removeContact(@RequestParam(value = "id") ObjectId id){
        MessageState msgState = new MessageState("ERROR", "Unable to remove contact!");
        Contact dbContact = this.contactService.findById(id);
        if(null!= dbContact){
            this.contactService.remove(dbContact);
            msgState.setCode("OK");
            msgState.setMessage("Successfully removed contact!");
        }

        return new ResponseEntity<>(msgState, HttpStatus.OK);
    }
}