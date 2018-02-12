package com.java.springboot.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ${Suresh M Kumar}
 *
 *         Dec 21, 2017
 */
@Component
public class ConfigFileModel {

	public String getLoginResource() {
		return loginResource;
	}

	public String getJwtTokentResource() {
		return jwtTokentResource;
	}

	@Value("${jwt.secret.key}")
	private String jwtSecretKey;

	@Value("${jwt.subject.encrypt.secret.key}")
	private String jwtSubEncryptSecretKey;

	@Value("${jwt.token.expiration}")
	private long jwtTokenExpirationTime;

	@Value("${jwt.token.prefix}")
	private String jwtTokentPrefix;

	@Value("${jwt.token.header.keyname}")
	private String jwtTokentHeaderKeyname;

	@Value("${login.resource}")
	private String loginResource;

	@Value("${jwt.token.resource}")
	private String jwtTokentResource;

	@Value("${project.title}")
	private String projectTitle;
	
	@Value("${project.description}")
	private String projectDescription;
	
	@Value("${project.version}")
	private String projectVersion;

	@Value("${project.termsOfServiceUrl}")
	private String projectTermsOfServiceUrl;

	@Value("${project.contact.name}")
	private String projectContactName;

	@Value("${project.contact.url}")
	private String projectContactUrl;

	@Value("${project.contact.email}")
	private String projectContactEmail;

	@Value("${project.license}")
	private String projectLicense;

	@Value("${project.license.url}")
	private String projectLicenseURL;
	
	@Value("${jwt.token.expiration.flag}")
	private boolean jwtTokenExpirationFlag;
	

	public String getProjectTitle() {
		return projectTitle;
	}

	public void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}

	public String getJwtSecretKey() {
		return jwtSecretKey;
	}

	public void setJwtSecretKey(String jwtSecretKey) {
		this.jwtSecretKey = jwtSecretKey;
	}

	public String getJwtSubEncryptSecretKey() {
		return jwtSubEncryptSecretKey;
	}

	public void setJwtSubEncryptSecretKey(String jwtSubEncryptSecretKey) {
		this.jwtSubEncryptSecretKey = jwtSubEncryptSecretKey;
	}

	public long getJwtTokenExpirationTime() {
		return jwtTokenExpirationTime;
	}

	public void setJwtTokenExpirationTime(long jwtTokenExpirationTime) {
		this.jwtTokenExpirationTime = jwtTokenExpirationTime;
	}

	public String getJwtTokentPrefix() {
		return jwtTokentPrefix;
	}

	public void setJwtTokentPrefix(String jwtTokentPrefix) {
		this.jwtTokentPrefix = jwtTokentPrefix;
	}

	public String getJwtTokentHeaderKeyname() {
		return jwtTokentHeaderKeyname;
	}

	public void setJwtTokentHeaderKeyname(String jwtTokentHeaderKeyname) {
		this.jwtTokentHeaderKeyname = jwtTokentHeaderKeyname;
	}

	public void setLoginResource(String loginResource) {
		this.loginResource = loginResource;
	}

	public void setJwtTokentResource(String jwtTokentResource) {
		this.jwtTokentResource = jwtTokentResource;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectVersion() {
		return projectVersion;
	}

	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}

	public String getProjectTermsOfServiceUrl() {
		return projectTermsOfServiceUrl;
	}

	public void setProjectTermsOfServiceUrl(String projectTermsOfServiceUrl) {
		this.projectTermsOfServiceUrl = projectTermsOfServiceUrl;
	}

	public String getProjectContactName() {
		return projectContactName;
	}

	public void setProjectContactName(String projectContactName) {
		this.projectContactName = projectContactName;
	}

	public String getProjectContactUrl() {
		return projectContactUrl;
	}

	public void setProjectContactUrl(String projectContactUrl) {
		this.projectContactUrl = projectContactUrl;
	}

	public String getProjectContactEmail() {
		return projectContactEmail;
	}

	public void setProjectContactEmail(String projectContactEmail) {
		this.projectContactEmail = projectContactEmail;
	}

	public String getProjectLicense() {
		return projectLicense;
	}

	public void setProjectLicense(String projectLicense) {
		this.projectLicense = projectLicense;
	}

	public String getProjectLicenseURL() {
		return projectLicenseURL;
	}

	public void setProjectLicenseURL(String projectLicenseURL) {
		this.projectLicenseURL = projectLicenseURL;
	}
	
	public void setJwtTokenExpirationFlag(boolean jwtTokenExpirationFlag) {
		this.jwtTokenExpirationFlag = jwtTokenExpirationFlag;
	}

	public boolean getJwtTokenExpirationFlag() {
		return jwtTokenExpirationFlag;
	}

}
