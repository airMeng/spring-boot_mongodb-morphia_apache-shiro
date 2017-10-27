package com.sample.rest.demo.springbootrest.repositories;

import com.sample.rest.demo.springbootrest.models.Role;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private Datastore datastore;

    @Override
    public Role getRole(String code) {
        return datastore.get(Role.class, code);
    }

    @Override
    public boolean saveOrUpdateRole(Role role) {
        if(null != role && null!=role.getCode() && 0 < role.getCode().trim().length())
            return null!=datastore.save(role);

        return false;
    }

    @Override
    public boolean updateRole(Role role) {
        if(null != role && null!=role.getCode() && 0 < role.getCode().trim().length()) {
            Role dbRole = getRole(role.getCode());
            if(null != dbRole){
                dbRole.setDescription(role.getDescription());
                dbRole.setLastModifiedDate(new Date());
                return null != this.datastore.save(dbRole);
            }
        }

        return false;
    }

    @Override
    public boolean deleteRole(Role role) {
        if(null != role && null!=role.getCode() && 0 < role.getCode().trim().length()) {
            Role dbRole = getRole(role.getCode());
            if(null != dbRole){
                dbRole.setDescription(role.getDescription());
                dbRole.setLastModifiedDate(new Date());
                return null != this.datastore.delete(dbRole);
            }
        }
        return false;
    }

    @Override
    public List<Role> listRoles() {
        return datastore.find(Role.class).order("name").asList();
    }
}