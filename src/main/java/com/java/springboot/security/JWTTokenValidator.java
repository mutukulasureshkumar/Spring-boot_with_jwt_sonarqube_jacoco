package com.java.springboot.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.java.springboot.model.UserDetailsImpl;
import com.java.springboot.utilities.AES;
import com.java.springboot.utilities.ConfigurationsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * @author ${Suresh M Kumar}
 *
 *         Dec 20, 2017
 */
@Component
public class JWTTokenValidator {

	@Autowired
	private ConfigurationsImpl configurations;
	
	@SuppressWarnings("unchecked")
	public UserDetailsImpl validate(String token) {
		Claims claims;
		String username = null;
		Set<GrantedAuthority> grantedAuthority = null;
		try{
			claims = Jwts.parser().setSigningKey(configurations.getConfigurations().getJWTSecretKey()).parseClaimsJws(token).getBody();
			username = AES.decrypt(claims.getSubject(), configurations.getConfigurations().getJWTSubjectEncryptionSecretKey());
			ArrayList<LinkedHashMap<String, String>> authorityList = (ArrayList<LinkedHashMap<String, String>>) claims.get("grantedAuthority");
			grantedAuthority = new HashSet<GrantedAuthority>();
			for (LinkedHashMap<String, String> map : authorityList) {
				if (map == null) {
					throw new IllegalArgumentException();
				}
				grantedAuthority.add(new SimpleGrantedAuthority(map.get("authority")));
			}
		}catch (Exception e) {
			throw new BadCredentialsException(e.getMessage());
		}
		return new UserDetailsImpl(username, null, grantedAuthority);
	}

}
