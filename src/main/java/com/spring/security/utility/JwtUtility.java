package com.spring.security.utility;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
	
	String secret="passwordcjncgjkldnfkglkfgkjhfgbnfgjncvkmggiljgfklgfklhfggmnbcjhcvjmnbcv";
	
	public String generateJwtToke(String username,Collection<? extends GrantedAuthority> collection) {
		
		Key key = Keys.hmacShaKeyFor(secret.getBytes());
		return Jwts.builder()
				.setIssuer("Sanat Dey")
				.setSubject("Jwt Token bayby")
				.claim("username", username)
				.claim("authorities", getAuthString(collection))
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getDate()+300000))
				.signWith(key)
				.compact();
				
	}
	
	private String getAuthString(Collection<? extends GrantedAuthority> auths) {
		StringBuilder authString = new StringBuilder(); 
		for(GrantedAuthority auth : auths) {
			authString.append(auth.toString());
		}
		return authString.toString();
	}

}
