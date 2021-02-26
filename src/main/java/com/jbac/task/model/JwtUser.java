package com.jbac.task.model;

public class JwtUser {
	/*A de tener los campos 
	 * Al momento de crear el JWT*/
	
	private String userName;
	private long id;
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}
