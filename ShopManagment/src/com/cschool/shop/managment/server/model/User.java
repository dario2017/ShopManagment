package com.cschool.shop.managment.server.model;

import java.util.Arrays;

public class User {
	private long id;
	public String login;
	private char[] password;
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public User(long id, String login, char[] password) {
		if (id <= 0) {
			throw new IllegalArgumentException("Id cannot be <= 0 ");
		}
		if (login == null || login.isEmpty()) {
			throw new IllegalArgumentException("Login cannot be null or empty");
		}
		if (password == null || password.length == 0) {
			throw new IllegalArgumentException("Password cannot be null or emty");
		}
		
		this.id = id;
		this.login = login;
		this.password = password;
	}
	
	public long getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + Arrays.toString(password) + "]";
	}

}
