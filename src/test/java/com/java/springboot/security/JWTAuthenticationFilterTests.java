package com.java.springboot.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.java.springboot.FudpApplication;
import com.java.springboot.utilities.TestData;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 19, 2017
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FudpApplication.class)
public class JWTAuthenticationFilterTests {
	
	@Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;
    
    @Autowired
    private TestData testData;
    
	public static final String JWT_TOKEN_HEADER_KEY = "authorization";
    
	@Before
	public void init() {
		mockMvc =			webAppContextSetup(webApplicationContext)
							.addFilter(springSecurityFilterChain)
							.build();
	}
	
	@Test
	public void testPositiveJwtAuthenticationFilter() throws Exception{
		mockMvc				.perform(get(testData.getTokenurl())
							.header(JWT_TOKEN_HEADER_KEY, testData.getValidToken()))
							.andExpect(status().isOk());
	}
	
	@Test
	public void testNegativeAuthenticationFilter1() throws Exception{
		mockMvc				.perform(get(testData.getTokenurl())
							.header(JWT_TOKEN_HEADER_KEY, testData.getInvalidToken()))
							.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void testNegativeAuthenticationFilter2() throws Exception{
		mockMvc				.perform(get(testData.getTokenurl())
							.header(JWT_TOKEN_HEADER_KEY, testData.getExpiredToken()))
							.andExpect(status().isUnauthorized());
	}
}
