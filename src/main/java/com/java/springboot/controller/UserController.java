package com.java.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.model.User;
import com.java.springboot.service.UserService;

/**
 * @author ${Suresh M Kumar}
 *
 * Jan 30, 2018
 */

@RequestMapping( value = "token/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUser(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public User saveUser(@RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public User updateUser(@RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public void deleteUser(@PathVariable String username) {
		userService.delete(username);
	}
	
}
