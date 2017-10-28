package com.sample.rest.demo.springbootrest.controllers;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShiroSpringController {

    @GetMapping(value = "login")
    public String login(){
        return "login";
    }

    @PostMapping(value = "login")
    public String login(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordToken upt = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();

        try {
            subject.login(upt);
        }
        catch (AuthenticationException ae){
            ae.printStackTrace();
            redirectAttributes.addFlashAttribute("errorText", "Invalid user/password!");
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }
}
