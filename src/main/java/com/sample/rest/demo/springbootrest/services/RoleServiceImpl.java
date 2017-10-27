package com.sample.rest.demo.springbootrest.services;

import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> list() {
        return this.roleRepository.listRoles();
    }

    @Override
    public Role findByCode(String code) {
        return this.roleRepository.getRole(code);
    }

    @Override
    public boolean save(Role role) {
        return this.roleRepository.saveOrUpdateRole(role);
    }

    @Override
    public boolean update(Role role) {
        return this.roleRepository.updateRole(role);
    }

    @Override
    public boolean remove(Role role) {
        return this.roleRepository.deleteRole(role);
    }

    @Override
    public List<Role> getRoleList(String[] roles){
        List<Role> listRoles = new ArrayList<>();
        if(null!=roles){
            for(String r : roles){
                if(null!=r && 0 < r.trim().length())
                    listRoles.add(this.roleRepository.getRole(r));
            }
        }
        return listRoles;
    }
}
