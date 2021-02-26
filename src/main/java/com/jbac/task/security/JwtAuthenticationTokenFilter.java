package com.jbac.task.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.jbac.task.contants.Contants;
import com.jbac.task.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
	

	
	public JwtAuthenticationTokenFilter() {
		super("/api/**");
		
	}
	
	/*Manejar la solicitud Q viene con el JWT
	en el header*/
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		String header = request.getHeader(Contants.AUTHORIZATION_HEADER);
		if(header == null|| !header.startsWith(Contants.BEARER_TOKEN)) {
			 throw new RuntimeException("JWT es incorrecto o no ha llegado");
			 
		}
		String authenticationToken = header.substring(7);
		JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);
		
		return getAuthenticationManager().authenticate(token);
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}



}
