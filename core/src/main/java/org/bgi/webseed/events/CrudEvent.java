package org.bgi.webseed.events;

import org.bgi.webseed.model.BaseDto;

public class CrudEvent<E extends BaseDto> extends GeneralEvent {

	private static final long serialVersionUID = -1733571800452027417L;

	private CrudEventType eventType;
	
	private E entity;
	
	public CrudEvent(Object source, CrudEventType eventType, E entity) {
		super(source);
		this.eventType = eventType;
		this.entity = entity;
	}
	
	public E getEntity(){
		return this.entity;
	}
	
	public CrudEventType getEventType(){
		return this.eventType;
	}
}
