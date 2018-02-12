package com.java.springboot.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.springboot.utilities.ConfigurationsImpl;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ${Suresh M Kumar}
 *
 * Jan 31, 2018
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	@Autowired
	ConfigurationsImpl configurationsImpl;	
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.java.springboot"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo(
								configurationsImpl.getConfigurations().getProjectTitle(), 
								configurationsImpl.getConfigurations().getProjectDescription(), 
								configurationsImpl.getConfigurations().getProjectVersion(), 
								configurationsImpl.getConfigurations().getProjectTermsOfServiceUrl(),
								new Contact(configurationsImpl.getConfigurations().getProjectContactName(), 
											configurationsImpl.getConfigurations().getProjectContactUrl(), 
											configurationsImpl.getConfigurations().getProjectContactEmail()), 
								configurationsImpl.getConfigurations().getProjectLicense(),
								configurationsImpl.getConfigurations().getProjectLicenseURL());  
		return apiInfo;
	}
	
/*	@SuppressWarnings("unchecked")
	private Predicate<String> postPaths() {
		return or(
				regex("/token.*"), 
				regex("/demo.*"), 
				regex("/secure.*"));
	}*/

}
