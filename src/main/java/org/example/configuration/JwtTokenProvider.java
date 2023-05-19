package org.example.configuration;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.security.JwtConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtTokenProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
//    private static String jwtSecretKey = JwtConfig.JWT_SECRET;
//    private static long jwtExpirationDate = JwtConfig.DURATION;
//    public JwtTokenProvider(JwtConfig jwtConfig) {
//        this.jwtSecretKey = j;
//        this.jwtExpirationDate = jwtExpirationDate;
//    }


    //generate token
//    public String generateToken(Authentication authentication) {
//
//        String roll = authentication.getName();
//
//        Date currentDate = new Date();
//        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
//        String token = Jwts.builder()
//                .setSubject(roll)
//                .setIssuedAt(new Date())
//                .setExpiration(expireDate)
//                .signWith(key())
//                .compact();
//
//        return token;
//    }

    public static String generateToken(String roll) {

        //String roll = authentication.getName();

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + JwtConfig.DURATION);
        String token = Jwts.builder()
                .setSubject(roll)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private static Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JwtConfig.JWT_SECRET));
    }

    // get roll from Jwt token
    public String decodeRoll(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return false;
    }


}
