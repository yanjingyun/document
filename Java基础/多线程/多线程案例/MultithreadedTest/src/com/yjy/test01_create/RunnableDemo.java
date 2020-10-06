package com.yjy.test01_create;

public class RunnableDemo implements Runnable {

	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "执行次数：" + i);
		}
	}

	public static void main(String[] args) {
		RunnableDemo demo = new RunnableDemo();
		new Thread(demo).start();
		new Thread(demo).start();
	}
}
