package org.example.helpers;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtAuth {
    public static String convertIntoJwt(String roll){
        String secretKey = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
        Date expirationDate = new Date(System.currentTimeMillis() + 3600000); // 1 hour
        String jwt = Jwts.builder()
                .claim("roll", roll)
                .setSubject("roll")
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                .compact();

        return jwt;
    }
}
