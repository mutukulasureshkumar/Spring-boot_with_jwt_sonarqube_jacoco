package com.java.springboot.security;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.java.springboot.model.User;
import com.java.springboot.model.UserDetailsImpl;
import com.java.springboot.service.UserService;
import com.java.springboot.utilities.ConfigurationsImpl;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 19, 2017
 */
public class AuthenticationFilterImpl extends AbstractAuthenticationProcessingFilter {
	
	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilterImpl.class);
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
	public static final String POST = "POST";
	
	@Autowired
	private ConfigurationsImpl configurations;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JWTTokenGenerator jwtTokenGenerator;
	
	public AuthenticationFilterImpl() {
		super("/secure/login");
	}
	
	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		LOGGER.info("In attemptAuthentication() method");

		if (!POST.equals(request.getMethod())) {
			throw new AuthenticationServiceException("Authentication method is not supported: " + request.getMethod());
		}
		
		String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
		String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
		
		/***Validating the requested user credentials***/
		if (username == null 
		||	password == null
		|| 	username.trim().length() == 0 
		|| 	password.trim().length() == 0) {
			throw new BadCredentialsException("Usarname and Password are must required!!");
		}

		/***Fetching the user details from database***/
		User user = userService.findByUsername(username.trim());

		/***Validating the user details***/
		if (user == null) {
	        throw new UsernameNotFoundException("User Not Found!!");
	    }
		
		if (!getEncoder().matches(password, user.getPassword())) {
	        throw new BadCredentialsException("Given password is not matching with the user.");
	    }
	    
	    /***Setting the user roles to granted authorities for authorization process***/
	    Set<GrantedAuthority> grantedAuthority = new HashSet<GrantedAuthority>();
        grantedAuthority.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName()));
	    //grantedAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    	//grantedAuthority.add(new SimpleGrantedAuthority("ADDUSER_READ_PRIVILEGE"));
    	
        /***Generating JWT Token******/
        UserDetailsImpl userDetailsImpl = new UserDetailsImpl(user.getUsername(), user.getPassword(), grantedAuthority);
    	String jwtToken = jwtTokenGenerator.generateToken(userDetailsImpl);
    	String jwtTokenPrefix = configurations.getConfigurations().getJwtTokentPrefix();
    	String jwtTokenResponseHeaderKey = configurations.getConfigurations().getJwtTokentHeaderKeyname();
        userDetailsImpl.setJwtToken(jwtTokenPrefix + jwtToken);

        /***Setting JWT Token and 200 status in response header***/
        response.setHeader("Access-Control-Expose-Headers", jwtTokenResponseHeaderKey);
        response.setHeader(jwtTokenResponseHeaderKey, userDetailsImpl.getJwtToken());
        response.setStatus(HttpServletResponse.SC_OK);
        
		return getAuthenticationManager().authenticate(userDetailsImpl);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		//chain.doFilter(request, response);
	}
	
	@Bean
	private PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
