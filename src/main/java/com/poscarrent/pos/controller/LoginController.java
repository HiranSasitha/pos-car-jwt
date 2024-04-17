package com.poscarrent.pos.controller;

import com.poscarrent.pos.dto.LoginDto;
import com.poscarrent.pos.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {
    @Autowired
    private JwtService jwtService;

    @PostMapping("authenticate")
    public String createTokenAndLogin(@RequestBody LoginDto dto) throws Exception {
return jwtService.createJwtToken(dto);
    }
}
