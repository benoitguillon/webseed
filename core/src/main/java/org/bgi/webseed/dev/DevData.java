package org.bgi.webseed.dev;

import java.util.ArrayList;
import java.util.List;

import org.bgi.webseed.firms.Firm;
import org.bgi.webseed.users.api.UserDto;

public class DevData {
	
	public static List<Firm> allFirms = new ArrayList<Firm>();
	
	private static void initializeFirms(){
		allFirms.add(new Firm(1L, "firm1", "firm1"));
		allFirms.add(new Firm(2L, "firm2", "firm2"));
	}
	

	static {
		initializeFirms();
	}
	
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
