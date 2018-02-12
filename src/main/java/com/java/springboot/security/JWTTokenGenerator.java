package com.java.springboot.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.java.springboot.utilities.AES;
import com.java.springboot.utilities.ConfigurationsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenGenerator {
	
	@Autowired
	private ConfigurationsImpl configurations;
	
	public String generateToken(UserDetails userDetails){
		Claims claim=Jwts.claims()
						.setSubject(AES.encrypt(userDetails.getUsername(), configurations.getConfigurations().getJWTSubjectEncryptionSecretKey()));
		claim.put("grantedAuthority", userDetails.getAuthorities());
		
		if(configurations.getConfigurations().getJWTTokenExpirationFlag()){
			return Jwts.builder()
					.setClaims(claim)
					.setExpiration(new Date(System.currentTimeMillis() + configurations.getConfigurations().getJWTTokenExpirationTime()))
					.signWith(SignatureAlgorithm.HS256, configurations.getConfigurations().getJWTSecretKey())
					.compact();
		}else{
			return Jwts.builder()
					.setClaims(claim)
					.signWith(SignatureAlgorithm.HS256, configurations.getConfigurations().getJWTSecretKey())
					.compact();
		}
	}
}
