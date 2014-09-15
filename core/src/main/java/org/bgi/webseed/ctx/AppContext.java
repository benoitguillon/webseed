package org.bgi.webseed.ctx;

import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.users.User;

public abstract class AppContext {

	private static ThreadLocal<AppContext> CURRENT_CTX = new ThreadLocal<AppContext>();

	public static AppContext getCurrentAppContext(){
		return CURRENT_CTX.get();
	}
	
	public static void setCurrentAppContext(AppContext currentContext){
		CURRENT_CTX.set(currentContext);
	}
	
	public static void clearCurrentAppContext(){
		CURRENT_CTX.set(null);
	}
	
	protected Firm currentFirm;
	
	protected User currentUser;
	
	public Firm getCurrentFirm() {
		return this.currentFirm;
	}

	public User getCurrentUser() {
		return this.currentUser;
	}

}
