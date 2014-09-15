package org.bgi.webseed.users.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bgi.webseed.ctx.AppContext;
import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.users.User;
import org.bgi.webseed.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value="/", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<UserDto> getUsers() {
		List<UserDto> result = new ArrayList<UserDto>();
		Firm firm = AppContext.getCurrentAppContext().getCurrentFirm();
		List<User> users = usersService.findByFirm(firm);
		for(User user : users){
			UserDto dto = new UserDto();
			dto.fromModel(user);
			result.add(dto);
		}
		return result;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public ResponseEntity<UserDto> getUser(@PathVariable long id) {
		UserDto result = new UserDto();
		if(id > 3){
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}
		result.setId(id);
		result.setUserName("userName");
		result.setLastName("lastName");
		result.setFirstName("firstName");
		return new ResponseEntity<UserDto>(result, HttpStatus.OK);
	}

}
