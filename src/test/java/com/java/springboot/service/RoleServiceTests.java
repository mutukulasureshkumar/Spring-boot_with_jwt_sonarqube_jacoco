package com.java.springboot.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.springboot.FudpApplication;
import com.java.springboot.config.JpaConfig;
import com.java.springboot.model.Role;
import com.java.springboot.repository.RoleRepository;
import com.java.springboot.utilities.TestData;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 19, 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FudpApplication.class, JpaConfig.class})
public class RoleServiceTests {

	@Resource
	private RoleRepository roleRepository;
	
	@Autowired
	TestData testData;
	
	@Test
	public void testAddRole(){
		roleRepository.save(testData.getRole());
		List<Role> role = roleRepository.findAll();
		assertEquals(testData.getRole().getName(), role.get(0).getName());  
	}
}
