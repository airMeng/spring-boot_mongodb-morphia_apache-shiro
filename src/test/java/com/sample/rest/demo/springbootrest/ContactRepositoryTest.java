package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.ContactRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactRepositoryTest extends SpringBootRestApplicationTests {

    @Autowired
    private ContactRepository contactRepository;


    @Test
    public void test10ListContact(){
        assertNotNull(this.contactRepository.listContact(null));
    }

    @Test
    public void test11ListContact(){
        assertNotNull(this.contactRepository.listContact(""));
    }

    @Test
    public void test12SaveOrUpdateContact(){
        Contact.ContactNumbers contactNumbers = new Contact.ContactNumbers("(0949) 993-0422", null, null, null);
        Contact.Socials socials = new Contact.Socials();
        socials.setFacebook("http://www.facebook.com/borgymanotoy");
        socials.setTwitter("http://www.twitter.com/borgymanotoy");
        Contact contact = new Contact("Borgy", "Manotory");
        contact.setContactNumbers(contactNumbers);
        contact.setOwner("ejsalipahmad");
        assertNotNull(this.contactRepository.saveOrUpdateContact(contact));
    }

    @Test
    public void test13GetContact(){
        assertNotNull(this.contactRepository.getContact("Borgy", "Manotory"));
    }

    @Test
    public void test14RemoveContact(){
        Contact dbContact = this.contactRepository.getContact("Borgy", "Manotory");
        assertNotNull(dbContact);
        assertTrue(this.contactRepository.deleteContact(dbContact));
    }
}