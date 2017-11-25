package com.security.bean;

import java.util.Arrays;

public class Bean {

	private String username;
	private String password;
	private String[] pics;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	@Override
	public String toString() {
		return "Bean [username=" + username + ", password=" + password + ", pics=" + Arrays.toString(pics) + "]";
	}

}
