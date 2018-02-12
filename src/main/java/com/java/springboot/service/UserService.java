package com.java.springboot.service;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.java.springboot.model.User;
import com.java.springboot.repository.UserRepository;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 18, 2017
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User findByUsername(String username) {
		if(username == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		return userRepository.findByUsername(username);
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		if(username == null || password == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		return userRepository.findByUsernameAndPassword(username, password);
	}

	public User save(User user){
		if(user == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		
		/***Encoding the password*****/
		user.setPassword(getEncoder().encode(user.getPassword()));
		user.setConfirmPassword(getEncoder().encode(user.getConfirmPassword()));
		return userRepository.save(user);
	}
	
	public User update(User user){
		if(user == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		//TODO:: check with existing record and encode the password.
		/***Encoding the password*****/
	/*	user.setPassword(getEncoder().encode(user.getPassword()));
		user.setConfirmPassword(getEncoder().encode(user.getConfirmPassword()));*/
		return userRepository.save(user);
	}
	
	public void delete(String username) {
		if(username == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		userRepository.deleteByUsername(username);
	}
	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@Bean
	private PasswordEncoder getEncoder() {
	    return new BCryptPasswordEncoder();
	}
}
