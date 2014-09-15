package org.bgi.webseed.events;

import org.bgi.webseed.firms.Firm;
import org.springframework.context.ApplicationEvent;

public class FirmEvent extends ApplicationEvent {

	private static final long serialVersionUID = -5909552738165368732L;

	private Firm firm;
	
	public FirmEvent(Object source, Firm firm) {
		super(source);
		this.firm = firm;
	}
	
	public Firm getFirm(){
		return this.firm;
	}

}
