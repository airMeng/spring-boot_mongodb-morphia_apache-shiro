package com.sample.rest.demo.springbootrest.repositories;

import com.sample.rest.demo.springbootrest.models.Role;

import java.util.List;

public interface RoleRepository {

    Role getRole(String code);
    boolean saveOrUpdateRole(Role role);
    boolean updateRole(Role role);
    boolean deleteRole(Role role);
    List<Role> listRoles();

}