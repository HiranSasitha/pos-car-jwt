package com.poscarrent.pos.service;

import com.poscarrent.pos.entity.Role;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {
    Role createNewRole(Role role);
}
