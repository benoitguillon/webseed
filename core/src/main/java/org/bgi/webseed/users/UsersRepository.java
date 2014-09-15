package org.bgi.webseed.users;

import java.util.List;

import org.bgi.webseed.firms.Firm;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {

	public User findByFirmAndUserName(Firm firm, String userName);
	
	public List<User> findByFirmSortByUserName(Firm firm);
}
