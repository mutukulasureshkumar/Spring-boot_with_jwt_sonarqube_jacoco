package com.java.springboot.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.java.springboot.repository.UserRepository;
import com.java.springboot.security.AuthenticationFilterImpl;
import com.java.springboot.security.AuthenticationProviderImpl;
import com.java.springboot.security.JWTAuthenticationFilter;
import com.java.springboot.security.SuccessHandler;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 20, 2017
 */
@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses=UserRepository.class)
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProviderImpl authenticationProviderImpl;

	@Override
	public AuthenticationManager authenticationManager(){
		return new ProviderManager(Collections.singletonList(authenticationProviderImpl));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.cors()
			.and()
			.authorizeRequests()
			.anyRequest().permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.headers().cacheControl();
		http.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public AuthenticationFilterImpl authenticationFilter(){
		AuthenticationFilterImpl authenticationFilterImpl = new AuthenticationFilterImpl();
		authenticationFilterImpl.setAuthenticationManager(authenticationManager());
		authenticationFilterImpl.setAuthenticationSuccessHandler(new SuccessHandler());
		return authenticationFilterImpl;
	}
	
	@Bean
	public JWTAuthenticationFilter jwtAuthenticationFilter(){
		JWTAuthenticationFilter jwtAuthenticationFilterImpl=new JWTAuthenticationFilter();
		jwtAuthenticationFilterImpl.setAuthenticationManager(authenticationManager());
		jwtAuthenticationFilterImpl.setAuthenticationSuccessHandler(new SuccessHandler());
		return jwtAuthenticationFilterImpl;
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
