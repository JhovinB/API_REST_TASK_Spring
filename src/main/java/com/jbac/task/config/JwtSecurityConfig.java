package com.jbac.task.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.stereotype.Component;

import com.jbac.task.security.JwtAuthenticationEntryPoint;
import com.jbac.task.security.JwtAuthenticationProvider;
import com.jbac.task.security.JwtAuthenticationTokenFilter;
import com.jbac.task.security.JwtSuccessHandler;

@EnableWebSecurity
@Component
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{
	/*Permite hacer la configuracion de la seguridad de la App*/
	
	/*Sobrescribir metodos para tener
	 * nuestras propias configuraciones de seguridad
	 * Analice un JWT cuando lo recibimos.
	 * */
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean
	public AuthenticationManager authenticationManager() {
		return new ProviderManager(Collections.singletonList(authenticationProvider));
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
		JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
		filter.setAuthenticationManager(authenticationManager());
		filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
		return filter;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.cors()
			.and()
			.csrf()
			.disable()
			.exceptionHandling().authenticationEntryPoint(entryPoint)
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	
	
	
	
}
