package com.yjy.test02_communication;

public class Synchronized_Object_Demo {
	static class NotifyRunnable implements Runnable {
		private final Object lock;

		public NotifyRunnable(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("notify开始，time=" + System.currentTimeMillis());
				try {
					Thread.sleep(3000); // 模拟业务逻辑执行时间
				} catch (Exception e) {
					e.printStackTrace();
				}
				lock.notify();
				System.out.println("notify结束，time=" + System.currentTimeMillis());
			}
		}
	}

	static class WaitRunnable implements Runnable {
		private final Object lock;

		public WaitRunnable(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				synchronized (lock) {
					System.out.println("wait开始，time=" + System.currentTimeMillis());
					lock.wait();
					System.out.println("wait结束，time=" + System.currentTimeMillis());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Object lock = new Object();
		new Thread(new WaitRunnable(lock), "线程1").start();
		new Thread(new NotifyRunnable(lock), "线程2").start();
	}
}
