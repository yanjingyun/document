package com.yjy.test99_other;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 案例：三个线程交替打印1~1000
 */
public class Test02 {
	public static void main(String[] args) {
		test1(); // synchronized方式
		test2(); // AtomicInteger方式
	}

	static Integer num1 = 0;

	private static void test1() {
		for (int i = 0; i < 3; i++) {
			new Thread(() -> {
				synchronized (Test02.class) {
					while (num1 < 1000) {
						System.out.println(Thread.currentThread().getName() + ":::" + num1++);
					}
				}
			}).start();
		}
	}

	static AtomicInteger num2 = new AtomicInteger(0);

	private static void test2() {
		for (int i = 0; i < 3; i++) {
			Thread thread = new Thread(() -> {
				while (num2.get() < 1000) {
					System.out.println(Thread.currentThread().getName() + ":::" + num2.getAndIncrement());
				}
			});
			thread.start();
		}
	}

}
