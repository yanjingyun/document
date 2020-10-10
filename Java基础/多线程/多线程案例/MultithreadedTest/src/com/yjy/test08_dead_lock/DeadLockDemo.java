package com.yjy.test08_dead_lock;

public class DeadLockDemo {
	private static Object lockA = new Object(); // 对象A
	private static Object lockB = new Object(); // 对象B

	public static void main(String[] args) {
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "开始执行");
			synchronized (lockA) { // 给对象A加锁
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lockB) { // 再给对象B加锁
					System.out.println(Thread.currentThread().getName() + "执行完毕...");
				}
			}
		}, "线程1").start();
		
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "开始执行");
			synchronized (lockB) { // 给对象B加锁
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lockA) { // 再给对象A加锁
					System.out.println(Thread.currentThread().getName() + "执行完毕...");
				}
			}
		}, "线程2").start();
	}

}
