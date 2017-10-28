package com.sample.rest.demo.springbootrest.controllers;


import com.sample.rest.demo.springbootrest.models.MessageState;
import com.sample.rest.demo.springbootrest.models.Role;
import com.sample.rest.demo.springbootrest.models.User;
import com.sample.rest.demo.springbootrest.services.RoleService;
import com.sample.rest.demo.springbootrest.services.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000", "https://borgymanotoy.auth0.com"})
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/register")
    public String register(@RequestParam(value = "error", required = false) boolean hasError, ModelMap map) {
        List<Role> availableRoles = this.roleService.list();
        map.addAttribute("availableRoles", availableRoles);

        String msg = null;
        if(hasError){
            msg = "Unable to register user!";
            map.addAttribute("hasError", hasError);
            map.addAttribute("msg", msg);
        }

        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestParam(value = "username", required = false, defaultValue = "") String username,
                               @RequestParam(value = "password", required = false, defaultValue = "") String password,
                               @RequestParam(value = "verifyPassword", required = false, defaultValue = "") String verifyPassword,
                               RedirectAttributes redirectAttributes) {

        System.out.println("\n\n");
        System.out.println("GET-MAPPING-REGISTER");
        System.out.println("-----------------------------------------------");
        System.out.println("[username]: " + username);
        System.out.println("[password]: " + password);
        System.out.println("[verifyPassword]: " + verifyPassword);
        System.out.println("-----------------------------------------------");
        System.out.println("\n\n");

        MessageState msgState = new MessageState();
        if(0 < username.trim().length() && 0 < password.trim().length() && 0 < verifyPassword.trim().length()){
            if(verifyPassword.equalsIgnoreCase(password)){
                User user = new User(username);
                user.setPassword(password);
                user.getRoles().add(this.roleService.findByCode("USER"));

                System.out.println("\n\n");
                System.out.println("[USER]: " + user.toString());
                System.out.println("\n\n");

                if(this.userService.save(user)){
                    msgState.setCode("OK");
                    msgState.setMessage("Registration Successful.");
                }
                else {
                    msgState.setCode("ERROR");
                    msgState.setMessage("Registration failed, user already exists!");
                }
            }
            else {
                msgState.setCode("ERROR");
                msgState.setMessage("Passwords does not match!");
            }
        }
        else {
            msgState.setCode("ERROR");
            msgState.setMessage("Please fill-up all the registration fields.");
        }

        redirectAttributes.addFlashAttribute("status", msgState);
        if("ERROR".equalsIgnoreCase(msgState.getCode())){
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("password", password);
            redirectAttributes.addFlashAttribute("verifyPassword", verifyPassword);
            redirectAttributes.addFlashAttribute("errorText", msgState.getMessage());
            return null;
        }
        else if("OK".equalsIgnoreCase(msgState.getCode())) {
            UsernamePasswordToken upt = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();

            try {
                subject.login(upt);
            }
            catch (AuthenticationException ae){
                ae.printStackTrace();
                redirectAttributes.addFlashAttribute("username", username);
                redirectAttributes.addFlashAttribute("password", password);
                redirectAttributes.addFlashAttribute("verifyPassword", verifyPassword);
                redirectAttributes.addFlashAttribute("errorText", "Invalid user/password!");
                return "redirect:/login";
            }

            return "redirect:/home";
        }

        return null;
    }

    @GetMapping(value = "/home")
    public String home(HttpSession session, ModelMap model) {
        String sessionId = session.getId();
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();

        model.addAttribute("sessionId", sessionId.toUpperCase());
        model.addAttribute("userInfo", username.toUpperCase());

        return "home";
    }

    @GetMapping(value = "/profile")
    public String profile(ModelMap model) {
        Subject subject = SecurityUtils.getSubject();
        User user = this.userService.findByUsername(subject.getPrincipal().toString());
        model.addAttribute("userInfo", user);
        return "profile";
    }

}