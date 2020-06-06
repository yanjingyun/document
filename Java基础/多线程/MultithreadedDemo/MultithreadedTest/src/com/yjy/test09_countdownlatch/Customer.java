package com.yjy.test09_countdownlatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 顾客类
 */
public class Customer implements Runnable {

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
