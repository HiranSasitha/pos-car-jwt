package com.poscarrent.pos.service.impl;

import com.poscarrent.pos.dto.LoginDto;
import com.poscarrent.pos.entity.Role;
import com.poscarrent.pos.entity.User;
import com.poscarrent.pos.repo.UserRepo;
import com.poscarrent.pos.service.JwtService;
import com.poscarrent.pos.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtServiceImpl implements JwtService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public String createJwtToken(LoginDto dto) throws Exception{
        String userName = dto.getUserName();
        String password = dto.getPassword();
        
        authenticate(userName,password);

        UserDetails userDetails = loadUserByUsername(userName);
        String generateToken = jwtUtil.generateJwtToken(userDetails);

        return generateToken;

    }

    private void authenticate(String userName, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));

        }catch (BadCredentialsException e){
            throw new Exception("invalid User",e);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findById(username).get();

        if(user != null) {
       return new org.springframework.security.core.userdetails.User(
               user.getUserName(),
               user.getPassword(),
               getAuthority(user)

       );


        }else {
            throw new UsernameNotFoundException("User Not Found"+username);
        }


    }

    public Set getAuthority(User user) {

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for(Role role:user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
return authorities;
    }
}
