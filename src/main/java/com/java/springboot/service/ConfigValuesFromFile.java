package com.java.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.springboot.config.Configurations;
import com.java.springboot.model.ConfigFileModel;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 21, 2017
 */
@Service
public class ConfigValuesFromFile implements Configurations{

	@Autowired
	private ConfigFileModel configFileModel;
	
	@Override
	public String getJWTSecretKey() {
		return configFileModel.getJwtSecretKey();
	}

	@Override
	public long getJWTTokenExpirationTime() {
		return configFileModel.getJwtTokenExpirationTime();
	}

	@Override
	public String getJWTSubjectEncryptionSecretKey() {
		return configFileModel.getJwtSubEncryptSecretKey();
	}

	@Override
	public String getJwtTokentPrefix() {
		return configFileModel.getJwtTokentPrefix();
	}

	@Override
	public String getJwtTokentHeaderKeyname() {
		return configFileModel.getJwtTokentHeaderKeyname();
	}

	@Override
	public String getTokenResource() {
		return configFileModel.getJwtTokentResource();
	}

	@Override
	public String getLogingResource() {
		return configFileModel.getLoginResource();
	}

	@Override
	public String getProjectTitle() {
		return configFileModel.getProjectTitle();
	}

	@Override
	public String getProjectDescription() {
		return configFileModel.getProjectDescription();
	}

	@Override
	public String getProjectVersion() {
		return configFileModel.getProjectVersion();
	}

	@Override
	public String getProjectTermsOfServiceUrl() {
		return configFileModel.getProjectTermsOfServiceUrl();
	}

	@Override
	public String getProjectContactName() {
		return configFileModel.getProjectContactName();
	}

	@Override
	public String getProjectContactUrl() {
		return configFileModel.getProjectContactUrl();
	}

	@Override
	public String getProjectContactEmail() {
		return configFileModel.getProjectContactEmail();
	}

	@Override
	public String getProjectLicense() {
		return configFileModel.getProjectLicense();
	}

	@Override
	public String getProjectLicenseURL() {
		return configFileModel.getProjectLicenseURL();
	}

	@Override
	public boolean getJWTTokenExpirationFlag() {
		return configFileModel.getJwtTokenExpirationFlag();
	}

}
