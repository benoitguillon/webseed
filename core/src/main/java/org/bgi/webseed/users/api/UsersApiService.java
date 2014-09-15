package org.bgi.webseed.users.api;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.users.User;
import org.bgi.webseed.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users")
public class UsersApiService {
	
	private static final Log logger = LogFactory.getLog(UsersApiService.class);
	
	@Autowired
	private UsersService usersService;
	
	public UsersApiService(){
		super();
		logger.info("Creating UsersService");
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UserDto> getUsers() {
		List<User> users = usersService.findByFirm(firm)
	}

}
