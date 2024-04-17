package com.poscarrent.pos.configuration;

import com.poscarrent.pos.service.JwtService;
import com.poscarrent.pos.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.Access;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.EOFException;
import java.io.IOException;
@Component

public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println(requestTokenHeader);

        String userName = null;
        String jwtToken = null;

        if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")){

            jwtToken = requestTokenHeader.substring(7);

            try {
                userName = jwtUtil.getUserNameFromToken(jwtToken);
                System.out.println(userName);

            }catch (IllegalArgumentException e){
                System.out.println("unable to get token");
            }catch (ExpiredJwtException e){
                System.out.println("token is Expire");
            }

        }else {
            System.out.println("jwt token is not start with Bearer");
        }

        if(userName != null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = jwtService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwtToken,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            }

        }
        filterChain.doFilter(request,response);
    }
}
