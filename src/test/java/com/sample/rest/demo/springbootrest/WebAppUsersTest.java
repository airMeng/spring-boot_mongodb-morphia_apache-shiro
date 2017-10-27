package com.sample.rest.demo.springbootrest;

import com.google.gson.Gson;
import com.sample.rest.demo.springbootrest.models.MessageState;
import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.models.User;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebAppUsersTest extends SpringBootRestApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private static String testObjectId = null;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test11ListUsers() throws Exception {
        String search = "";
        mockMvc.perform(get("/api/users")
                .param("search", search))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void test12AddUser() throws Exception {
        String username = "dummyUser";
        String password = "test";
        String verifyPassword = "test";
        String[] roles = { "USER", "ACTUATOR" };


        mockMvc.perform(post("/api/user")
                    .param("username", username)
                    .param("password", password)
                    .param("verifyPassword", verifyPassword)
                    .param("roles", roles))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test13GetUserDetails() throws Exception {
        String username = "dummyUser";
        mockMvc.perform(get("/api/user")
                .param("un", username))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test14RemoveUser() throws Exception {
        String username = "dummyUser";
        mockMvc.perform(delete("/api/user").param("un", username))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
