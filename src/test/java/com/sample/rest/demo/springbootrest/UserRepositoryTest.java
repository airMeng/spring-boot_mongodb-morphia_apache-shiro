package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.repositories.RoleRepository;
import com.sample.rest.demo.springbootrest.repositories.UserRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest extends SpringBootRestApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test01ListUsers_null(){
        assertNotNull(this.userRepository.listUsers(null));
    }

    @Test
    public void test02ListUsers_blank(){
        assertNotNull(this.userRepository.listUsers(""));
    }

    @Test
    public void test03ListUsers(){
        assertNotNull(this.userRepository.listUsers("dummyUser"));
    }

    @Test
    public void test10AddUser_null_user_null_role(){
        assertFalse(this.userRepository.saveOrUpdateUser(null));
    }

    @Test
    public void test11AddUser_blank_user_null_role(){
        assertFalse(this.userRepository.saveOrUpdateUser(new User()));
    }

    @Test
    public void test12AddUser_null_role(){
        User dummyUser = new User("dummyUser1");
        dummyUser.setPassword("letmein123");
        assertFalse(this.userRepository.saveOrUpdateUser(dummyUser));
    }

    @Test
    public void test13AddUser_with_user_and_role(){
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleRepository.getRole("USER"));

        User dummyUser = new User("dummyUser2");
        dummyUser.setPassword("letmein123");
        dummyUser.setRoles(roles);
        assertTrue(this.userRepository.saveOrUpdateUser(dummyUser));
    }

    @Test
    public void test14UpdateUser_null_user_and_role(){
        assertFalse(this.userRepository.updateUser(null));
    }

    @Test
    public void test15UpdateUser_blank_user_null_role(){
        assertFalse(this.userRepository.updateUser(new User()));
    }

    @Test
    public void test16UpdateUser_user_no_role(){
        User user = new User("dummyUser1");
        assertFalse(this.userRepository.updateUser(user));
    }

    @Test
    public void test17UpdateUser_user_change_role(){
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleRepository.getRole("USER"));

        User user = new User("dummyUser2");
        user.setPassword("pasudlako123");
        user.setRoles(roles);
        assertTrue(this.userRepository.updateUser(user));
    }

    @Test
    public void test18UpdateUser_edit_user_change_role(){
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleRepository.getRole("ADMIN"));

        User user = new User("dummyUser2");
        user.setPassword("itlogmonoyorensbingkamonayinitpa");
        user.setRoles(roles);
        assertTrue(this.userRepository.updateUser(user));
    }

    @Test
    public void test19GetUser_null_username(){
        assertNull(this.userRepository.getUser(null));
    }

    @Test
    public void test20GetUser_blank_username(){
        assertNull(this.userRepository.getUser(""));
    }

    @Test
    public void test21GetUser_not_existing_username(){
        assertNull(this.userRepository.getUser("joserizal"));
    }

    @Test
    public void test22GetUser_existing_username(){
        assertNotNull(this.userRepository.getUser("dummyUser2"));
    }


    @Test
    public void test23RemoveUser_null_user(){
        assertFalse(this.userRepository.deleteUser(null));
    }

    @Test
    public void test24RemoveUser_blank_user(){
        assertFalse(this.userRepository.deleteUser(""));
    }

    @Test
    public void test25RemoveUser_user_not_existing(){
        assertFalse(this.userRepository.deleteUser("gardobiskotso"));
    }

    @Test
    public void test26RemoveUser_existing_user(){
        assertTrue(this.userRepository.deleteUser("dummyUser2"));
    }



/*
    @Test
    public void testCreateAdministrator(){
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleRepository.getRole("ADMIN"));

        User dummyUser = new User("administrator");
        dummyUser.setPassword("pasudlako123");
        dummyUser.setRoles(roles);
        assertTrue(this.userRepository.saveOrUpdateUser(dummyUser));
    }

    @Test
    public void testCreateAdministratorBorgy(){
        List<Role> roles = new ArrayList<>();
        roles.add(this.roleRepository.getRole("ADMIN"));
        roles.add(this.roleRepository.getRole("ACTUATOR"));
        roles.add(this.roleRepository.getRole("USER"));

        User dummyUser = new User("borgymanotoy");
        dummyUser.setPassword("pasudlako123");
        dummyUser.setRoles(roles);
        assertTrue(this.userRepository.saveOrUpdateUser(dummyUser));
    }
*/


}
