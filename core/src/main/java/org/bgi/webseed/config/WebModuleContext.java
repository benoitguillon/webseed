package org.bgi.webseed.config;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.menu.MenuRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

public abstract class WebModuleContext {
	
	private static final Log logger = LogFactory.getLog(WebModuleContext.class);
	
	private MenuRegistry menuRegistry;

	@PostConstruct
	public void initializeModule(){
		logger.info(String.format("Initializing WebModuleContext %s", this.getClass().getCanonicalName()));
	}
	
	public abstract void registerMenus(final MenuRegistry menuRegistry);
	
	public MenuRegistry getMenuRegistry() {
		return menuRegistry;
	}

	@Autowired
	@Required
	public void setMenuRegistry(MenuRegistry menuRegistry) {
		this.menuRegistry = menuRegistry;
	}
	
	
	
}
