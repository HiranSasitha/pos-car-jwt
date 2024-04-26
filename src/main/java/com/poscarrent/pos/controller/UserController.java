package com.poscarrent.pos.controller;

import com.poscarrent.pos.entity.Role;
import com.poscarrent.pos.entity.User;
import com.poscarrent.pos.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/register-new-user")
    public User registerNewUser(@RequestBody User user){
        return userService.createNewUser(user);
    }
    @PostConstruct
    public void intRoleAndUser(){
        userService.initRoleAndUser();
    }

    @GetMapping("/for-admin")
    @PreAuthorize("hasRole('ADMIN')") //ROLE_ADMIN
    public String admin(){
        System.out.println("hiran");
        return "admin";
    }

    @GetMapping("/for-user")
    @PreAuthorize("hasRole('USER')")// web security enable prepost
    public String user(){
        return "user";
    }
}
