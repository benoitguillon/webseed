package org.bgi.webseed.config;

import org.bgi.webseed.menu.MenuItem;
import org.bgi.webseed.menu.MenuRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main application configuration
 * 
 * @author guillon
 */
@Configuration
@ComponentScan(basePackages={"org.bgi.webseed"})
public class RootConfig {
	
	@Bean
	public MenuRegistry menuRegistry(){
		MenuRegistry registry = new MenuRegistry();
		registry.addMainMenu(new MenuItem("menu.admin.title", "admin"));
		MenuItem myprofileItem = new MenuItem("menu.myprofile.title", "myprofile");
		myprofileItem.addSubItem(new MenuItem("menu.myprofile.changepwd.title", "myprofile/changepwd"));
		registry.addMainMenu(myprofileItem);
		return registry;
	}

}
