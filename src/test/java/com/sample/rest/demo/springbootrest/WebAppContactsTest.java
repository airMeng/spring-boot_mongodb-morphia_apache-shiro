package com.sample.rest.demo.springbootrest;

import com.google.gson.Gson;
import com.sample.rest.demo.springbootrest.models.Contact;
import com.sample.rest.demo.springbootrest.models.MessageState;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebAppContactsTest extends SpringBootRestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static String testObjectId = null;

    private static Gson gson = new Gson();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test10ListContact() throws Exception {
        mockMvc.perform(get("/api/listContact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void test11AddContact() throws Exception {
        Contact.ContactNumbers contactNumbers = new Contact.ContactNumbers("(0949) 993-0422", null, null, null);
        Contact.Socials socials = new Contact.Socials();
        socials.setFacebook("http://www.facebook.com/borgymanotoy");
        socials.setTwitter("http://www.twitter.com/borgymanotoy");
        Contact contact = new Contact("Borgy", "Manotory");
        contact.setContactNumbers(contactNumbers);
        contact.setSocials(socials);
        contact.setOwner("ejsalipahmad");

        String addContactJson = gson.toJson(contact);

        System.out.println("\n\n[CONTACT]: ");
        System.out.println(addContactJson);
        System.out.println("\n");


        ResultActions resultActions = mockMvc.perform(post("/api/addContact")
                .content(addContactJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

        MvcResult result = resultActions.andReturn();
        if(null!=result){
            MessageState msgState = gson.fromJson(result.getResponse().getContentAsString(), MessageState.class);
            testObjectId = msgState.getResultObjectId();
            System.out.println("\t[content]: " + testObjectId);
        }

    }

    @Test
    public void test12GetContact() throws Exception {
        mockMvc.perform(get("/api/getContact").param("id", testObjectId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test13UpdateContact() throws Exception {
        Contact.ContactNumbers contactNumbers = new Contact.ContactNumbers("(0949) 993-0422", null, "(082) 300-5321", "(082) 300-8088");
        Contact.Socials socials = new Contact.Socials();
        socials.setFacebook("http://www.facebook.com/borgymanotoy");
        socials.setTwitter("http://www.twitter.com/borgymanotoy");
        socials.setInstagram("http://www.instagram.com/borgymanotoy");
        Contact contact = new Contact("Borgy", "Manotory");
        contact.setContactNumbers(contactNumbers);
        contact.setSocials(socials);
        contact.setOwner("ejsalipahmad");

        String updateJson = gson.toJson(contact);

        mockMvc.perform(post("/api/updateContact")
                .content(updateJson)
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void test14RemoveContact() throws Exception {
        mockMvc.perform(post("/api/removeContact").param("id", testObjectId))
                .andDo(print())
                .andExpect(status().isOk());
    }

}