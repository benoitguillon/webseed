package org.bgi.webseed.api.users;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.dev.DevData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users")
public class UsersService {
	
	private static final Log logger = LogFactory.getLog(UsersService.class);
	
	public UsersService(){
		super();
		logger.info("Creating UsersService");
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UserDto> getUsers(){
		return DevData.getAllUsers();
	}

}
