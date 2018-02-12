package com.java.springboot.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.java.springboot.FudpApplication;
import com.java.springboot.config.JpaConfig;
import com.java.springboot.model.User;
import com.java.springboot.repository.UserRepository;
import com.java.springboot.utilities.TestData;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 19, 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FudpApplication.class, JpaConfig.class})
public class UserServiceTests {
	
	@Resource
	private UserRepository userRepository;
	
	@Autowired
	TestData testData;
	
	@Test
	public void testAddUser(){
		userRepository.save(testData.getUser());
		User user = userRepository.findByUsername(testData.getUser().getUsername());
		assertEquals(testData.getUser().getUsername(), user.getUsername());  
	}
}
