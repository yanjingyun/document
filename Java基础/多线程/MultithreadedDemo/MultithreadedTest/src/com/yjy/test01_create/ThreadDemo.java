package com.yjy.test01_create;

public class ThreadDemo extends Thread {

	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "Ö´ÐÐ´ÎÊý£º" + i);
		}
	}
	
	public static void main(String[] args) {
		ThreadDemo demo1 = new ThreadDemo();
		ThreadDemo demo2 = new ThreadDemo();
		demo1.start();
		demo2.start();
	}
}
