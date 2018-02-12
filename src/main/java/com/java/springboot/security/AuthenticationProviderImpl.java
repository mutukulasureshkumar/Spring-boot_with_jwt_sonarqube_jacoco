package com.java.springboot.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.java.springboot.model.UserDetailsImpl;

@Component
public class AuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider  {
	
	@Override
	public boolean supports(Class<?> authentication) {
		return UserDetailsImpl.class.isAssignableFrom(authentication);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authenticationToken) {
		return (UserDetailsImpl) authenticationToken ;
	}
	
}
