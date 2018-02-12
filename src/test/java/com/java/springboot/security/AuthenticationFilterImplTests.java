package com.java.springboot.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.java.springboot.FudpApplication;
import com.java.springboot.config.JpaConfig;
import com.java.springboot.repository.RoleRepository;
import com.java.springboot.repository.UserRepository;
import com.java.springboot.utilities.TestData;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 19, 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {FudpApplication.class, JpaConfig.class})
public class AuthenticationFilterImplTests {
	
	@Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;
    
	@Resource
	private UserRepository userRepository;
	
	@Resource
	private RoleRepository rolrRepository;
	
	@Autowired
	TestData testData;
	
	@Value("${test.data.password}")
	private String password;
	
	public static final String FORM_USERNAME_KEY = "username";
	public static final String FORM_PASSWORD_KEY = "password";
    
	@Before
	public void init()  {
		rolrRepository.save(testData.getRole());
		userRepository.save(testData.getUser());
		mockMvc = MockMvcBuilders	.webAppContextSetup(webApplicationContext)
									.addFilter(springSecurityFilterChain)
									.build();
	}
	
	@Test
	public void testPositiveAuthenticationFilter() throws Exception{
		mockMvc				.perform(post(testData.getLoginURL())
							.param(FORM_USERNAME_KEY, testData.getUser().getUsername())
							.param(FORM_PASSWORD_KEY, password))
							.andExpect(status().isOk());
	}
	
	@Test
	public void testNegativeAuthenticationFilter1() throws Exception{
		mockMvc				.perform(post(testData.getLoginURL())
							.param(FORM_USERNAME_KEY, testData.getUser().getUsername()+"WorngUsername")
							.param(FORM_PASSWORD_KEY, testData.getUser().getPassword()+"WorngPassword"))
							.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testNegativeAuthenticationFilter2() throws Exception{
		mockMvc				.perform(post(testData.getLoginURL()+"WorngLoginURL")
							.param(FORM_USERNAME_KEY, testData.getUser().getUsername())
							.param(FORM_PASSWORD_KEY, testData.getUser().getPassword()))
							.andExpect(status().isNotFound());
	}
}
