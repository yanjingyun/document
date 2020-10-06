package com.yjy.test06_wait_parent_thread;

/**
 * 使用join方法
 */
public class JoinThreadDemo {
	
	static class JoinRunnableTest implements Runnable {
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "开始执行");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "执行完毕...");
		}
	}

	public static void main(String[] args) {
		JoinRunnableTest test = new JoinRunnableTest();
		Thread threadA = new Thread(test, "线程A");
		Thread threadB = new Thread(test, "线程B");
		threadA.start();
		threadB.start();
		
		System.out.println("主线程开始执行");
		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("主线程执行完毕。。。");
	}
}
