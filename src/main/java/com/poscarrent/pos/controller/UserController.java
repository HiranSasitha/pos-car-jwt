package com.poscarrent.pos.controller;

import com.poscarrent.pos.entity.Role;
import com.poscarrent.pos.entity.User;
import com.poscarrent.pos.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/create-new-role")
    public User registerNewUser(@RequestBody User user){
        return userService.createNewUser(user);
    }
    @PostConstruct
    public void intRoleAndUser(){
        userService.initRoleAndUser();
    }
}
