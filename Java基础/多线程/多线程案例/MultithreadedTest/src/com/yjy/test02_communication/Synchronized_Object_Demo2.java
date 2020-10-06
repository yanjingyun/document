package com.yjy.test02_communication;

/**
 * 使用Object的wait和notify实现线程间通信机制
 */
public class Synchronized_Object_Demo2 {
	private int i = 1;
	private Object obj = new Object();

	/**
	 * 奇数打印
	 */
	public void odd() {
		synchronized (obj) {
			while (i <= 10) {
				if (i % 2 == 1) {
					System.out.println("基数" + i);
					i++;
					obj.notify(); // 唤醒偶数线程打印
				} else {
					try {
						obj.wait(); // 等待偶数线程打印
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * 偶数打印
	 */
	public void even() {
		synchronized (obj) {
			while (i <= 10) {
				if (i % 2 == 0) {
					System.out.println("偶数" + i);
					i++;
					obj.notify(); // 唤醒奇数线程打印
				} else {
					try {
						obj.wait(); // 等待奇数线程打印
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Synchronized_Object_Demo2 demo = new Synchronized_Object_Demo2();
		// 开启奇数线程打印
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.odd();
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.even();
			}
		}).start();
	}
}
