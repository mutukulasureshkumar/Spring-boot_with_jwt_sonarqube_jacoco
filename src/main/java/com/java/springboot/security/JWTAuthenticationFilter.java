package com.java.springboot.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.java.springboot.model.UserDetailsImpl;
import com.java.springboot.utilities.ConfigurationsImpl;

/**
 * @author ${Suresh M Kumar}
 *
 *         Dec 20, 2017
 */
public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private ConfigurationsImpl configurations;
	
	@Autowired
	private JWTTokenValidator JWTValidator;

	public JWTAuthenticationFilter() {
		super("/token/*");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse arg1)
			throws AuthenticationException, IOException, ServletException {

		/***Getting JWT Token header from request******/
		String header = httpServletRequest.getHeader(configurations.getConfigurations().getJwtTokentHeaderKeyname());

		if (header == null || !header.startsWith(configurations.getConfigurations().getJwtTokentPrefix())) {
			throw new BadCredentialsException("Invalid JWT Token");
		}

		/***Getting JWT Token from header******/
		String recievedToken = header.substring(7);
		
		/***Validating JWT Token ******/
		UserDetailsImpl userDetailsImpl = JWTValidator.validate(recievedToken);
		
		return getAuthenticationManager().authenticate(userDetailsImpl);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

}
