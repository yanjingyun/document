package com.yjy.test05_threadLocal;

import java.util.Random;

/**
 * 统计某个方法的耗时时间
 */
public class ThreadLocalDemo {

	// static final类型，保证该变量不能指向其它对象
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>();

	public static final void start() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}

	public static final long end() {
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}
	
	static class ShowTime implements Runnable {
		@Override
		public void run() {
			ThreadLocalDemo.start(); // 执行方法前
			try {
				// 模拟方法执行所需时间
				Random random = new Random();
				int sleepTime = random.nextInt(2000) + 1000;
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			System.out.println(Thread.currentThread().getName() + "执行方法所用时间：" + ThreadLocalDemo.end()); // 执行方法后
		}
	}

	public static void main(String[] args) throws Exception {
		ShowTime showTime = new ShowTime();
		new Thread(showTime, "线程1").start();
		new Thread(showTime, "线程2").start();
		new Thread(showTime, "线程3").start();
	}

}
