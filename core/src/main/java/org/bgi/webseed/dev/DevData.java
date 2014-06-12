package org.bgi.webseed.dev;

import java.util.ArrayList;
import java.util.List;

import org.bgi.webseed.api.users.UserDto;

public class DevData {
	
	public static List<UserDto> getAllUsers(){
		List<UserDto> result = new ArrayList<>();
		UserDto user1 = new UserDto();
		user1.setId(1L);
		user1.setLogin("ibra");
		user1.setFirstName("Zlatan");
		user1.setLastName("Ibrahimovic");
		user1.setAddress("Camps des Loges");
		result.add(user1);
		
		UserDto user2 = new UserDto();
		user2.setId(2L);
		user2.setLogin("tiago");
		user2.setFirstName("Tiago");
		user2.setLastName("Silva");
		user2.setAddress("Camps des Loges");
		result.add(user2);
		
		return result;
		
	}

}