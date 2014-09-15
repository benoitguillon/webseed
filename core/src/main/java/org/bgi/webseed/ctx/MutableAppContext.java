package org.bgi.webseed.ctx;

import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.users.User;

public class MutableAppContext extends AppContext {
	
	public void setFirm(Firm firm){
		this.currentFirm = firm;
	}
	
	public void setUser(User user){
		this.currentUser = user;
	}
	
	

}
