package org.bgi.webseed.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.util.Assert;

public class MenuItem {
	
	private MenuItem parent = null;
	
	private String title;
	
	private String destination;
	
	private List<MenuItem> subItems = new ArrayList<MenuItem>();
	
	public MenuItem(final String title, final String destination){
		this.title = title;
		this.destination = destination;
	}

	public String getTitle() {
		return title;
	}

	public String getDestination() {
		return destination;
	}
	
	public void addSubItem(final MenuItem subItem){
		Assert.isTrue(subItem.isRoot(), "Cannot override a MenuItem parent");
		Assert.notNull(subItem);
		this.subItems.add(subItem);
		subItem.setParent(subItem);
	}
	
	public List<MenuItem> getSubItems(){
		return Collections.unmodifiableList(this.subItems);
	}
	
	public boolean isRoot(){
		return this.parent == null;
	}
	
	public void setParent(final MenuItem parent){
		this.parent = parent;
	}

}
