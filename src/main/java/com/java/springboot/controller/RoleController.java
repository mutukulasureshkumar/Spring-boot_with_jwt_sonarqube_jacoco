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

import com.java.springboot.model.Role;
import com.java.springboot.service.RoleService;

/**
 * @author ${Suresh M Kumar}
 *
 * Jan 30, 2018
 */

@RequestMapping( value = "token/role", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public List<Role> getRoles() {
		return roleService.getAllRoles();
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public Role saveUser(@RequestBody Role role) {
		return roleService.save(role);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public Role updateRole(@RequestBody Role role) {
		return roleService.save(role);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{username}")
	@PreAuthorize("hasRole('SUPER_ADMIN')")
	public void deleteRole(@PathVariable String rolename) {
		roleService.delete(rolename);
	}
}