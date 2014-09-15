package org.bgi.webseed.users;

import java.util.List;

import javax.transaction.Transactional;

import org.bgi.webseed.firms.Firm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public User findByFirmAndUserName(Firm firm, String userName) {
		return usersRepository.findByFirmAndUserName(firm, userName);
	}

	@Override
	public List<User> findByFirm(Firm firm) {
		return usersRepository.findByFirmSortByUserName(firm);
	}

}
