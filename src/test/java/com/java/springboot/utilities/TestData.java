package com.java.springboot.utilities;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.java.springboot.model.Role;
import com.java.springboot.model.User;


/**
 * @author ${Suresh M Kumar}
 *
 * Dec 19, 2017
 */
@Component
public class TestData {
	
	@Value("${test.data.userid}")
	private long userid;
	@Value("${test.data.firstName}")
	private String firstName;
	@Value("${test.data.lastName}")
	private String lastName;
	@Value("${test.data.username}")
	private String username;
	@Value("${test.data.password}")
	private String password;
	@Value("${test.data.roleid}")
	private long roleid;
	@Value("${test.data.roleName}")
	private String roleName;
	@Value("${test.data.loginurl}")
	private String loginURL;
	@Value("${test.data.validToken}")
	private String validToken;
	@Value("${test.data.expiredToken}")
	private String expiredToken;
	@Value("${test.data.invalidToken}")
	private String invalidToken;
	@Value("${test.data.tokenurl}")
	private String tokenurl;
	
	public Role getRole(){
		Role role =new Role();
		role.setId(roleid);
		role.setName(roleName);
		return role;
	}
	
	public User getUser(){
		Date date = new Date();
		User user = new User();
		user.setRole(getRole());
		user.setId(userid);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(getEncoder().encode(password));
		user.setConfirmPassword(getEncoder().encode(password));
		user.setCreatedDate(date);
		user.setLastModifiedDate(date);
		return user;
	}
	
	public String getLoginURL(){
		return loginURL;
	}
	
	public String getValidToken() {
		return validToken;
	}

	public String getExpiredToken() {
		return expiredToken;
	}

	public String getInvalidToken() {
		return invalidToken;
	}

	public String getTokenurl() {
		return tokenurl;
	}
	
	@Bean
	private PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
