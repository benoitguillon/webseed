package org.bgi.webseed.menu;

import org.springframework.util.Assert;

public class MenuRegistry {
	
	private MenuItem root = new MenuItem("ROOT", "no-where");
	
	public void addMainMenu(final MenuItem mainMenu){
		Assert.notNull(mainMenu);
		root.addSubItem(mainMenu);
	}

}
