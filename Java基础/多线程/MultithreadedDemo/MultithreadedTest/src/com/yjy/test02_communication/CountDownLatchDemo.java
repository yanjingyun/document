package com.yjy.test02_communication;

import java.util.concurrent.CountDownLatch;

/**
 * 教练训练运动员，教练需要等待所有运动员就位才开始
 */
public class CountDownLatchDemo {
	
	/**
	 * 运动员方法，由运动员线程调用
	 */
	static class Racer extends Thread {
		private CountDownLatch countDownLatch;
		public Racer(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "正在准备...");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "准备完毕，countDownLatch减1...");
			countDownLatch.countDown();
		}
	}
	
	/**
	 * 教练方法，由运动员线程调用
	 */
	static class Coach extends Thread {
		
		private CountDownLatch countDownLatch;
		public Coach(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "等待所有运动员准备...");
			try {
				countDownLatch.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("所有运动员已就绪，" + name + "开始训练！");
		}
	}
	
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3); //设置等待的运动员3个
		Racer racer1 = new Racer(countDownLatch);
		racer1.setName("运动员1");
		racer1.start();
		Racer racer2 = new Racer(countDownLatch);
		racer2.setName("运动员1");
		racer2.start();
		Racer racer3 = new Racer(countDownLatch);
		racer3.setName("运动员1");
		racer3.start();

		Coach coach = new Coach(countDownLatch);
		coach.setName("教练");
		coach.start();
	}
	
}
