package com.poscarrent.pos.service;

import com.poscarrent.pos.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public interface JwtService {
    String createJwtToken(LoginDto dto) throws Exception;
}
