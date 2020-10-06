package com.yjy.test09_countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Main {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(3);
		Customer customer1 = new Customer(countDownLatch, "张三");
		Customer customer2 = new Customer(countDownLatch, "李四");
		Customer customer3 = new Customer(countDownLatch, "王五");
		Waiter waiter = new Waiter(countDownLatch);
		
		new Thread(customer1).start();
		new Thread(customer2).start();
		new Thread(customer3).start();
		new Thread(waiter).start();
	}
}
