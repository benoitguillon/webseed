package org.bgi.webseed.config;

import org.bgi.webseed.config.WebModuleContext;
import org.bgi.webseed.menu.MenuItem;
import org.bgi.webseed.menu.MenuRegistry;

public class Module1Context extends WebModuleContext {

	@Override
	public void registerMenus(MenuRegistry menuRegistry) {
		MenuItem menuItem = new MenuItem("menu.compliance.title", "compliance");
		menuRegistry.addMainMenu(menuItem);
	}
	
	
	

}
