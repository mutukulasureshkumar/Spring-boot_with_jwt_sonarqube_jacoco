package com.java.springboot.service;

import org.springframework.stereotype.Service;

import com.java.springboot.config.Configurations;

/**
 * @author ${Suresh M Kumar}
 *
 *         Dec 21, 2017
 */
@Service
public class ConfigValuesFromDatabase implements Configurations {

	// @Override
	public String getJWTSecretKey() {
		// TODO Auto-generated method stub
		return null;
	}

	// @Override
	public long getJWTTokenExpirationTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	// @Override
	public String getJWTSubjectEncryptionSecretKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJwtTokentPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getJwtTokentHeaderKeyname() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTokenResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogingResource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectTermsOfServiceUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectContactName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectContactUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectContactEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectLicense() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProjectLicenseURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getJWTTokenExpirationFlag() {
		// TODO Auto-generated method stub
		return false;
	}

}
