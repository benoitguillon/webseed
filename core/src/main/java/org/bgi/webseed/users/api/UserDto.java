package org.bgi.webseed.users.api;

import org.bgi.webseed.model.BaseDto;
import org.bgi.webseed.users.User;

public class UserDto extends BaseDto<User> {
	
	private long id;
	
	private long firmId;
	
	private String firmName;
	
	private String userName;
	
	private String lastName;
	
	private String firstName;
	
	private String email;
	
	@Override
	public User toModel() {
		User user = new User();
		user.setId(this.id);
		user.setUserName(this.userName);
		user.setFirstName(this.firmName);
		user.setLastName(this.lastName);
		user.setEmail(this.email);
		return user;
	}
	
	@Override
	public void fromModel(User user) {
		this.setId(user.getId());
		this.setUserName(user.getUserName());
		this.setFirstName(user.getFirstName());
		this.setLastName(user.getLastName());
		this.setEmail(user.getEmail());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFirmId() {
		return firmId;
	}

	public void setFirmId(long firmId) {
		this.firmId = firmId;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
