package com.poscarrent.pos.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${app.secret}")  //use application.properties value
    private String jwtSecret ;

    @Value("${app.jwtExpiration}")
    private int jwtExpiration;

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T>T getClaimFromToken(String token, Function<Claims,T> claimsResolver) {
        final Claims claims = getAllClaimsByToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsByToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody();
    }

   public Boolean validateToken(String token, UserDetails userDetails){
        final String TokenUserName =getUserNameFromToken(token);
        return (TokenUserName.equals(userDetails.getUsername()) && !isTokenExpire(token));
}

    public Boolean isTokenExpire(String token){
        final Date expire = getClaimFromToken(token,Claims::getExpiration);
        return expire.before(new Date());
    }

    public String generateJwtToken (UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+jwtExpiration))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }


}
