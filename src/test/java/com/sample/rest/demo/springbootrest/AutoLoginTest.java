package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.services.RoleService;
import com.sample.rest.demo.springbootrest.services.UserService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutoLoginTest extends SpringBootRestApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @Before
    public void initialize(){
        List<Role> roles;

        if(null==this.roleService.list() || 0 == this.roleService.list().size()){
            roleService.save(new Role("ADMIN"));
            roleService.save(new Role("USER"));
            roleService.save(new Role("ACTUATOR"));
        }

        roles = this.roleService.list();

        if(null==this.userService.list(null) || 0 == this.userService.list(null).size()){
            User user = new User("test_user", "test");
            user.setRoles(roles);

            System.out.println("[USER]: " + user.toString());
            System.out.println("[VALID]: " + user.validate());

            this.userService.save(user);
        }
    }

    @Test
    public void test01UserExists(){
        String username = "test_user";
        String password = "test";

        User user = this.userService.findByUsername(username);
        TestCase.assertNotNull(user);
    }

    @After
    public void cleanUp(){
        this.userService.remove("test_user");
    }
}
