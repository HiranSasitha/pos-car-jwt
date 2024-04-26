package com.poscarrent.pos.controller;

import com.poscarrent.pos.dto.LoginDto;
import com.poscarrent.pos.dto.UserResponseDto;
import com.poscarrent.pos.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()

public class LoginController {
    @Autowired
    private JwtService jwtService;

    @PostMapping("authenticate")
    public UserResponseDto createTokenAndLogin(@RequestBody LoginDto dto) throws Exception {
        return jwtService.createJwtToken(dto);
    }
}
