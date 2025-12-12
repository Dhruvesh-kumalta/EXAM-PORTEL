package com.exam.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final String SECRET="a4f9d2b78c1e45a0c3f8b9e2d7c6f1a4e8b3d9c4f2a1b6e7c9d0a3b5e6f8c2d1\\r\\n";
	private final SecretKey key=Keys.hmacShaKeyFor(SECRET.getBytes());
	private final long EXPIRATION_TIME=1000*60*60;
	
	
	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date())                  // new method
	            .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	            .signWith(key, Jwts.SIG.HS256)         // updated way
	            .compact();
	}
	public String extractUsername(String token) {

	    Claims claims = Jwts.parser()
	            .verifyWith(key)   // key = SecretKey
	            .build()
	            .parseSignedClaims(token)
	            .getPayload();

	    return claims.getSubject();
	}
public boolean validateToken(String username, UserDetails userDetails,String token) {
		
		
		//TODO-check if username is same as username in userDetails
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		//TODO-check if the token is not expired
		
	}
    private Claims extractAllClaims(String token) {// use for finding token expire
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Date extractExpiration(String token) {
        return extractAllClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
