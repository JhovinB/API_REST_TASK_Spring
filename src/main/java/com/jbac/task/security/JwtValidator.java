package com.jbac.task.security;

import org.springframework.stereotype.Component;

import com.jbac.task.contants.Contants;
import com.jbac.task.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	
	public JwtUser validate(String token) {
		JwtUser jwtUser=null;
		
		try {
			//Extraer los datos del JWT
			Claims body = Jwts.parser()
					.setSigningKey(Contants.YOUR_SECRET)
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JwtUser();
			jwtUser.setUserName(body.getSubject());
			jwtUser.setId(Long.parseLong((String)body.get(Contants.USER_ID)));
		} catch (Exception e) {
			System.out.println(e);
		}
		return jwtUser;
	}
}
