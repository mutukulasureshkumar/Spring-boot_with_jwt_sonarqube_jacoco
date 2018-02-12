package com.java.springboot.service;

import java.security.InvalidParameterException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springboot.model.Role;
import com.java.springboot.repository.RoleRepository;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 19, 2017
 */
@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Role save(Role role){
		if(role == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		return roleRepository.save(role);
	}
	
	public Role update(Role role){
		if(role == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		//TODO:: check with existing record and encode the password.
		/***Encoding the password*****/
	/*	user.setPassword(getEncoder().encode(user.getPassword()));
		user.setConfirmPassword(getEncoder().encode(user.getConfirmPassword()));*/
		return roleRepository.save(role);
	}
	
	public void delete(String rolename) {
		if(rolename == null)
			throw new InvalidParameterException("Invalid input parameter !!");
		roleRepository.deleteByName(rolename);
	}
	
	
	public List<Role> getAllRoles(){
		return roleRepository.findAll();
	}
}
