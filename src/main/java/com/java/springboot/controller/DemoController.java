package com.java.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.springboot.model.Role;
import com.java.springboot.model.User;
import com.java.springboot.service.RoleService;
import com.java.springboot.service.UserService;

@RestController
public class DemoController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	
	//@PreAuthorize("hasRole('SUPER_ADMIN')")
	@RequestMapping(value = "/register/user", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		return userService.save(user);
	}
	
	@RequestMapping(value = "/register/role", method = RequestMethod.POST)
	//@PreAuthorize("hasRole('SUPER_ADMIN')")
	public Role registerRole(@RequestBody Role role) {
		return roleService.save(role);
	}
	
	@RequestMapping(value = "/token/user", method = RequestMethod.POST)
	//@PreAuthorize("hasRole('ADMIN') and hasPermission('ADDUSER','READ')")
	@PreAuthorize("hasRole('ADMIN')")
	public User getUser(@RequestBody User user) {
		return userService.findByUsername(user.getUsername());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/token/check")
	public String tokenCheck() {
		return "Token Validated Sucessfully!!!";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public String test() {
		return "Test called!!!";
	}
}
