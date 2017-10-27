package com.sample.rest.demo.springbootrest.services;

import com.sample.rest.demo.springbootrest.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> list();
    List<Role> getRoleList(String[] roles);
    Role findByCode(String code);
    boolean save(Role role);
    boolean update(Role role);
    boolean remove(Role role);

}
