package org.bgi.webseed.users;

import java.util.List;

import org.bgi.webseed.firms.Firm;

public interface UsersService {
	
	public User findByFirmAndUserName(Firm firm, String userName);

	public List<User> findByFirm(Firm firm);
	
}
