package com.yjy.singleton.v3;

/**
 * 方式3：静态内部类-单例模式
 */
public class Singleton {
	private static class SingletonHolder {
		private static final Singleton singleton = new Singleton();
	}
	private Singleton() {}
	public static final Singleton getInstance() {
		return SingletonHolder.singleton;
	}
	
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}