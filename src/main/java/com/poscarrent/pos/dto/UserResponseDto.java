package com.poscarrent.pos.dto;

import com.poscarrent.pos.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Access;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String jwtToken;
    private User user;

}
