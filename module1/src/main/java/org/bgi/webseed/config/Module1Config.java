package org.bgi.webseed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Module1Config {

	@Bean
	public Module1Context complianceContext(){
		return new Module1Context();
	}
	
	
}
