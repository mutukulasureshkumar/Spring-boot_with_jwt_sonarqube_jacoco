package com.java.springboot.utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.java.springboot.config.Configurations;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 21, 2017
 */
@Configuration
public class ConfigurationsImpl{
	
	public static final String FROM_FILE = "FILE";
	
	@Value("${congif.value.source}")
	private String configValuesSource;
	
	@Autowired
	@Qualifier("configValuesFromFile")
	private Configurations configurationsFromFile;
	
	@Autowired
	@Qualifier("configValuesFromDatabase")
	private Configurations configurationsFromDatabase;
	
	public Configurations getConfigurations() {
		if(FROM_FILE.equals(configValuesSource))
			return configurationsFromFile;
		else
			return configurationsFromDatabase;
	}

}