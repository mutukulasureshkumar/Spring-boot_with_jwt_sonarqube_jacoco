package com.java.springboot.security;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class BasePermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
		if ((auth == null) || !(targetDomainObject instanceof String) || !(permission instanceof String)){
            return false;
        }
        return hasPrivilege(auth, targetDomainObject.toString().toUpperCase(), permission.toString().toUpperCase());
		
	}
	
	private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
	    for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
	        if (grantedAuth.getAuthority().startsWith(targetType)) {
	            if (grantedAuth.getAuthority().contains(permission)) {
	                return true;
	            }
	        }
	    }
	    return false;
	}

	@Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(auth, targetType.toUpperCase(), permission.toString().toUpperCase());
    }

}