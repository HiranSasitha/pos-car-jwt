package com.poscarrent.pos.service.impl;

import com.poscarrent.pos.entity.Role;
import com.poscarrent.pos.repo.RoleRepo;
import com.poscarrent.pos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public Role createNewRole(Role role) {
        return roleRepo.save(role);
    }
}
