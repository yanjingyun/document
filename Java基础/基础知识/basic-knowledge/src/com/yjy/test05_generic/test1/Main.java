package com.yjy.test05_generic.test1;

// 获取泛型类中的参数
public class Main {

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		System.out.println(dao.getClazz());
	}
}
