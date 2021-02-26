package com.jbac.task.security;

import org.springframework.stereotype.Component;

import com.jbac.task.contants.Contants;
import com.jbac.task.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator {
	/* Generar el JWT*/
	
	public String generate(JwtUser jwtUser) {
		
		Claims claims = Jwts.claims()
				.setSubject(jwtUser.getUserName());
		claims.put(Contants.USER_ID, String.valueOf(jwtUser.getId()));
		
		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS256, Contants.YOUR_SECRET)
				.compact();
	}

}
