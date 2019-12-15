package com.yuziak.Hotelshi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import com.yuziak.Hotelshi.security.jwt.JwtConfigurer;
import com.yuziak.Hotelshi.security.jwt.JwtTokenProvider;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private  JwtTokenProvider jwtTokenProvider;

	private static final String[] ADMIN_ENDPOINT = {};
	private static final String[] LOGIN_ENDPOINT = {"/api/**","/login","/login/ver",};


	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.httpBasic().disable()
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(LOGIN_ENDPOINT).permitAll()
				.antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
				.anyRequest().authenticated()
				.and().
				apply(new JwtConfigurer(jwtTokenProvider));
	}
}
