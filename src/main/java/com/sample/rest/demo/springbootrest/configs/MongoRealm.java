package com.sample.rest.demo.springbootrest.configs;

import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.services.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MongoRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //Intentionally blank
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("\n\n");
        System.out.println("AUTHENTICATION");
        System.out.println("---------------------------------------------");
        System.out.println("[PRINCIPAL]: " + authenticationToken.getPrincipal().toString());
        System.out.println("---------------------------------------------");
        System.out.println("\n\n");

        User user = userService.findByUsername(authenticationToken.getPrincipal().toString());
        if(null!=user){
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getClass().getName());
        }
        else
            throw new AuthenticationException();
    }
}
