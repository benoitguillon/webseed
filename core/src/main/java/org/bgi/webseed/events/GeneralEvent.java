package org.bgi.webseed.events;

import org.springframework.context.ApplicationEvent;

public class GeneralEvent extends ApplicationEvent {

	private static final long serialVersionUID = 4459163126113134554L;

	public GeneralEvent(Object source) {
		super(source);
	}

}
