package com.java.springboot.config;

/**
 * @author ${Suresh M Kumar}
 *
 *         Dec 21, 2017
 */
public interface Configurations {
	
	public String getJWTSecretKey();

	public long getJWTTokenExpirationTime();

	public String getJWTSubjectEncryptionSecretKey();

	public String getJwtTokentPrefix();

	public String getJwtTokentHeaderKeyname();

	public String getTokenResource();

	public String getLogingResource();

	public String getProjectTitle();

	public String getProjectDescription();

	public String getProjectVersion();

	public String getProjectTermsOfServiceUrl();

	public String getProjectContactName();

	public String getProjectContactUrl();

	public String getProjectContactEmail();

	public String getProjectLicense();

	public String getProjectLicenseURL();
	
	public boolean getJWTTokenExpirationFlag();
	
}
