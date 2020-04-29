package com.yjy.test06_comparator.test2;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	
	private static final UserComparator userComparator = new UserComparator();

	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		list.add(new User("testAA1", Date.valueOf("2019-12-12")));
		list.add(new User("testAA2", Date.valueOf("2019-12-18")));
		list.add(new User("testAA3", Date.valueOf("2019-12-16")));
		
		Collections.sort(list, userComparator);
		for (User user : list) {
			System.out.println(user);
		}
	}
}
