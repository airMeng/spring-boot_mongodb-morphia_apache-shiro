package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.models.User;
import junit.framework.TestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest extends SpringBootRestApplicationTests {

    @Test
    public void test10UserValidate_null_infos(){
        User user = new User();
        TestCase.assertFalse(user.validate());
    }

    @Test
    public void test11UserValidate_only_username(){
        User user = new User("dummyUser");
        TestCase.assertFalse(user.validate());
    }

    @Test
    public void test12UserValidate_username_password(){
        User user = new User("dummyUser");
        user.setPassword("letmein123");
        TestCase.assertFalse(user.validate());
    }

    @Test
    public void test13UserValidate_username_password_with_role(){
        List<Role> listRoles = new ArrayList<>();
        listRoles.add(new Role("TEST"));

        User user = new User("dummyUser");
        user.setPassword("letmein123");
        user.setRoles(listRoles);
        TestCase.assertTrue(user.validate());
    }

    @Test
    public void test14UserValidate_username_password_with_role(){
        User user = new User("dummyUser");
        user.setPassword("letmein123");
        user.getRoles().add(new Role("TEST"));
        TestCase.assertTrue(user.validate());
    }
}