package com.yjy.test03_reflect.other;

import java.sql.Date;

public class User extends AuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -589494222826774285L;

	private String username;
	private String password;
	private Date birthday;
	
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", birthday=" + birthday + "]";
	}
}
