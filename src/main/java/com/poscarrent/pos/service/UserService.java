package com.poscarrent.pos.service;

import com.poscarrent.pos.entity.Role;
import com.poscarrent.pos.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createNewUser(User user);
     void initRoleAndUser();
}
