package org.bgi.webseed.users.api;

public class UserDto {
	
	private long id;
	
	private String login;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
