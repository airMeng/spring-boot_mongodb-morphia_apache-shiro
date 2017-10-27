package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.Contact;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactTest extends SpringBootRestApplicationTests {

    @Test
    public void test01_null_contact(){
        Contact contact = null;
        assertNull(contact);
    }

    @Test
    public void test01_blank_contact(){
        Contact contact = new Contact();
        assertFalse(contact.validate());
    }

    @Test
    public void test01_contact_owner_only(){
        Contact contact = new Contact("ejsalipahmad");
        assertFalse(contact.validate());
    }

    @Test
    public void test01_contact_details_no_cnumbers_socials(){
        Contact contact = new Contact("Borgy", "Manotory");
        contact.setOwner("ejsalipahmad");
        assertFalse(contact.validate());
    }

    @Test
    public void test01_contact_details_cnumbers_no_socials(){
        Contact.ContactNumbers contactNumbers = new Contact.ContactNumbers("(0949) 993-0422", null, null, null);
        Contact contact = new Contact("Borgy", "Manotory");
        contact.setContactNumbers(contactNumbers);
        contact.setOwner("ejsalipahmad");
        assertTrue(contact.validate());
    }

    @Test
    public void test01_contact_details_cnumbers_socials(){
        Contact.ContactNumbers contactNumbers = new Contact.ContactNumbers("(0949) 993-0422", null, null, null);
        Contact.Socials socials = new Contact.Socials();
        socials.setFacebook("http://www.facebook.com/borgymanotoy");
        socials.setTwitter("http://www.twitter.com/borgymanotoy");
        Contact contact = new Contact("Borgy", "Manotory");
        contact.setContactNumbers(contactNumbers);
        contact.setOwner("ejsalipahmad");
        assertTrue(contact.validate());
    }

}
