package com.yjy.test02_communication;

import java.util.concurrent.Semaphore;

/**
 * 模拟8个工人使用3台机器工作，1台机器同一时间只能1人操作
 */
public class SemaphoreDemo {

	static class Worker implements Runnable {
		private Semaphore semaphore = new Semaphore(3);
		
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			try {
				semaphore.acquire();
				System.out.println(name + "获取到机器，开始工作...");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				System.out.println(name + "使用完毕，释放机器");
				semaphore.release();
			}
		}
	}
	
	public static void main(String[] args) {
		int workers = 8; //代表工人数
		Worker worker = new Worker();
		for (int i = 0; i < workers; i++) {
			new Thread(worker, "工人" + i).start();
		}
	}
}
