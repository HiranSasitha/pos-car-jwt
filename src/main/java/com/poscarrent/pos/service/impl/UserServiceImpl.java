package com.poscarrent.pos.service.impl;

import com.poscarrent.pos.entity.Role;
import com.poscarrent.pos.entity.User;
import com.poscarrent.pos.repo.RoleRepo;
import com.poscarrent.pos.repo.UserRepo;
import com.poscarrent.pos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createNewUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public void initRoleAndUser(){

        Role adminRole = new Role();
        Role userRole = new Role();

        if (!roleRepo.existsById("Admin")){
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");

            roleRepo.save(adminRole);
        }
        System.out.println(adminRole.getRoleName());


        if (!roleRepo.existsById("User")){
            userRole.setRoleName("User");
            userRole.setRoleDescription("User role");
            roleRepo.save(userRole);
        }
        System.out.println(userRole.getRoleName());

        if(!userRepo.existsById("admin123")){
            User user = new User();
            user.setUserName("admin123");
            user.setUserFirstName("Hiran");
            user.setUserLastName("Henarath");
            user.setPassword(getPasswordEncode("admin@123"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            user.setRoles(roles);
            userRepo.save(user);
        }

        if(!userRepo.existsById("user123")){
            User user = new User();
            user.setUserName("user123");
            user.setUserFirstName("Sasitha");
            user.setUserLastName("Senarath");
            user.setPassword(getPasswordEncode("user@123"));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRoles(roles);
            userRepo.save(user);
        }
    }

    public String getPasswordEncode(String password){
        return passwordEncoder.encode(password);
    }
}
