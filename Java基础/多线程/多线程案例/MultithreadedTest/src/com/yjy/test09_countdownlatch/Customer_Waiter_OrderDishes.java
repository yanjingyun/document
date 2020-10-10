package com.yjy.test09_countdownlatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 顾客点菜
 */
public class Customer_Waiter_OrderDishes {
	
	// 顾客类
	static class Customer implements Runnable {

		private CountDownLatch countDownLatch;
		private String name;
		
		public Customer(CountDownLatch countDownLatch, String name) {
			this.countDownLatch = countDownLatch;
			this.name = name;
		}
		
		@Override
		public void run() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			Random random = new Random();
			System.out.println(LocalDateTime.now().format(formatter) + " " + name + "出发去饭店");
			try {
				Thread.sleep((long) (random.nextDouble() * 3000) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(LocalDateTime.now().format(formatter) + " " + name + "到了饭店");
			countDownLatch.countDown();
		}
	}
	
	// 服务员
	static class Waiter implements Runnable {
		private CountDownLatch countDownLatch;
		
		public Waiter(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			System.out.println(LocalDateTime.now().format(formatter) + " 服务员等待上菜");
			try {
//				countDownLatch.await();
				boolean await = countDownLatch.await(2, TimeUnit.SECONDS);
				if (await) {
					System.out.println("所有人都到齐，开始上菜！");
				} else {
					System.out.println("有人迟到了，服务员开始上菜");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println(LocalDateTime.now().format(formatter) + " 服务员开始上菜");
		}
	}

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		new Thread(new Customer(countDownLatch, "张三")).start();
		new Thread(new Customer(countDownLatch, "李四")).start();
		new Thread(new Customer(countDownLatch, "王五")).start();
		new Thread(new Waiter(countDownLatch)).start();
	}
}
