package com.yjy.test02_communication;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 教练训练运动员，教练需要等待所有运动员就位才开始
 */
public class CountDownLatchDemo {
	
	/**
	 * 运动员方法，由运动员线程调用
	 */
	static class Racer implements Runnable {
		private CountDownLatch countDownLatch;
		public Racer(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "正在准备...");
			int sleepTime = 0;
			try {
				Random random = new Random();
				sleepTime = random.nextInt(2000) + 1000;
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + "准备完毕，准备时间为" + sleepTime + "ms");
			countDownLatch.countDown();
		}
	}
	
	/**
	 * 教练方法，由运动员线程调用
	 */
	static class Coach implements Runnable {
		
		private CountDownLatch countDownLatch;
		public Coach(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println(name + "等待所有运动员准备...");
			try {
				// 方式1：一直等待直到所有返回
//				countDownLatch.await(); 
				
				// 方式2：等待2秒，超时则直接返回flase
				boolean flag = countDownLatch.await(2, TimeUnit.SECONDS); //
				if (flag) {
					System.out.println("所有运动员已就绪，" + name + "开始训练！");
				} else {
					System.out.println("有运动员的准备时间超过2秒，教练不耐烦了！");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3); //设置等待的运动员3个
		new Thread(new Coach(countDownLatch), "教练").start();
		
		new Thread(new Racer(countDownLatch), "运动员1").start();
		new Thread(new Racer(countDownLatch), "运动员2").start();
		new Thread(new Racer(countDownLatch), "运动员3").start();
	}
}
