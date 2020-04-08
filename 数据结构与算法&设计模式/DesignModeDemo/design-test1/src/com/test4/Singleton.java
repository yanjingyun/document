package com.test4;

/**
 * 枚举实现
 */
public enum Singleton {
	INSTANCE;
	public void method1() {
		System.out.println("枚举实现单例模式...");
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.INSTANCE;
		Singleton s2 = Singleton.INSTANCE;
		System.out.println(s1 == s2);
		s1.method1();
	}
}
