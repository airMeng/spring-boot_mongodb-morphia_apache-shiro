package com.sample.rest.demo.springbootrest;

import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.repositories.RoleRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static junit.framework.TestCase.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleRepositoryTest extends SpringBootRestApplicationTests {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void test1ListRoles(){
        assertNotNull(this.roleRepository.listRoles());
    }

    @Test
    public void test10AddRole_null_role(){
        Role dummyRole = null;
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test11AddRole_blank_role(){
        Role dummyRole = new Role("");
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test12AddRole_only_code(){
        Role dummyRole = new Role("DUMMY1");
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test13AddRole_code_description(){
        Role dummyRole = new Role("DUMMY2");
        dummyRole.setDescription("This is just a dummy role for testing.");
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test14AddRole_code_description_lastmodifieddate(){
        Role dummyRole = new Role("DUMMY3");
        dummyRole.setDescription("This is just a dummy role for testing.");
        dummyRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(dummyRole);
    }

    @Test
    public void test15GetRole_null_code(){
        assertNull(this.roleRepository.getRole(null));
    }

    @Test
    public void test16GetRole_blank_code(){
        assertNull(this.roleRepository.getRole(""));
    }

    @Test
    public void test17GetRole_not_exist_code(){
        assertNull(this.roleRepository.getRole("burikat"));
    }

    @Test
    public void test18GetRole_exist_code(){
        assertNotNull(this.roleRepository.getRole("DUMMY1"));
    }

    @Test
    public void test19UpdateRole_null(){
        assertFalse(this.roleRepository.updateRole(null));
    }

    @Test
    public void test20UpdateRole_blank(){
        assertFalse(this.roleRepository.updateRole(new Role("")));
    }

    @Test
    public void test21UpdateRole_code(){
        assertTrue(this.roleRepository.updateRole(new Role("DUMMY1")));
    }

    @Test
    public void test22UpdateRole_code_description(){
        Role dummyUpdateRole = new Role("DUMMY2");
        dummyUpdateRole.setDescription("Orayt! This is the update role description.");
        assertTrue(this.roleRepository.updateRole(dummyUpdateRole));
    }

    @Test
    public void test23UpdateRole_code_description_lastmodifieddate(){
        Role dummyUpdateRole = new Role("DUMMY3");
        dummyUpdateRole.setDescription("Orayt! This is the update role description.");
        dummyUpdateRole.setLastModifiedDate(new Date());
        assertTrue(this.roleRepository.updateRole(dummyUpdateRole));
    }

    @Test
    public void test24DeleteRole_null(){
        assertFalse(this.roleRepository.deleteRole(null));
    }

    @Test
    public void test25DeleteRole_blank(){
        assertFalse(this.roleRepository.deleteRole(new Role("")));
    }

    @Test
    public void test26DeleteRole_code(){
        assertTrue(this.roleRepository.deleteRole(new Role("DUMMY3")));
    }

    @Test
    public void test27RemoveOtherDummyData(){
        assertTrue(this.roleRepository.deleteRole(new Role("DUMMY1")));
        assertTrue(this.roleRepository.deleteRole(new Role("DUMMY2")));
    }

    @Test
    public void testCreateRoleAdminUserActuator(){
        Role adminRole = new Role("ADMIN");
        adminRole.setDescription("Administrator Role");
        adminRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(adminRole);

        Role actuatorRole = new Role("ACTUATOR");
        actuatorRole.setDescription("Actuator Role");
        actuatorRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(actuatorRole);

        Role userRole = new Role("USER");
        userRole.setDescription("User Role");
        userRole.setLastModifiedDate(new Date());
        this.roleRepository.saveOrUpdateRole(userRole);
    }


}