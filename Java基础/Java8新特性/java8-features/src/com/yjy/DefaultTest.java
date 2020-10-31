package com.yjy;

/**
 * JDK1.8增加了Default关键字，使得我们的接口类里边可以有具体的方法
 */
public class DefaultTest {

	public static void main(String[] args) {
		User user = new User();
		System.out.println(user.getUsername());
		System.out.println(user.getDefaultUsername());
		System.out.println(IUser.getStaticUsername());
	}

	static class User implements IUser {
		@Override
		public String getUsername() {
			return "myUsername";
		}

	}
}

interface IUser {
	String getUsername();

	default String getDefaultUsername() {
		return "defaultUsername";
	}
	
	static String getStaticUsername() {
		return "staticUsername";
	}
}
