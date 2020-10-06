package com.yjy.test09_countdownlatch;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

public class Waiter implements Runnable {
	private CountDownLatch countDownLatch;
	
	public Waiter(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		System.out.println(LocalDateTime.now().format(formatter) + " 服务员等待上菜");
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(LocalDateTime.now().format(formatter) + " 服务员开始上菜");
	}
}
