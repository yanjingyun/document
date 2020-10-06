package com.yjy.test02_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印数字1~10
 * 使用Lock和Condition实现线程间通信机制
 */
public class Lock_Condition_Demo2 {
	
	private int i = 1;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	/**
	 * 奇数打印
	 */
	public void odd() {
		lock.lock();
		try {
			while (i <= 10) {
				if (i % 2 == 1) {
					System.out.println("基数" + i);
					i++;
					condition.signal(); // 唤醒偶数线程打印
				} else {
					try {
						condition.await(); // 等待偶数线程打印
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

	/**
	 * 偶数打印
	 */
	public void even() {
		lock.lock();
		try {
			while (i <= 10) {
				if (i % 2 == 0) {
					System.out.println("基数" + i);
					i++;
					condition.signal(); // 唤醒偶数线程打印
				} else {
					try {
						condition.await(); // 等待偶数线程打印
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		Lock_Condition_Demo2 demo = new Lock_Condition_Demo2();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.odd();
			}
		}).start(); // 开启奇数线程打印
		new Thread(new Runnable() {
			@Override
			public void run() {
				demo.even();
			}
		}).start(); // 开启偶数线程打印
	}
}
