package com.yjy.test06_comparator.test1;

import java.sql.Date;

public class User implements Comparable<User> {
	private String username;
	private Date birthday;
	
	@Override
	public int compareTo(User o) {
		if (this.birthday.after(o.birthday)) {
			return 1;
		}
		return -1;
	}
	
	public User(String username, Date birthday) {
		this.username = username;
		this.birthday = birthday;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", birthday=" + birthday + "]";
	}
}
