package com.java.springboot.model;

import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl extends UsernamePasswordAuthenticationToken implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private Set<GrantedAuthority> grantedAuthority;
	private String jwtToken;

	public UserDetailsImpl(String username, String password, Set<GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.username=(String) username;
		this.grantedAuthority= authorities;
	}
	
	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Set<GrantedAuthority> getAuthorities() {
		return grantedAuthority;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	@Override
	public String getPassword() {
		return (String) super.getCredentials();
	}

}
