package com.yjy.test07_producer_consumer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟生产者和消费者场景：AtomicInteger实现
 */
public class ProducerAndConsumer3 {
	public static void main(String[] args) {
		AtomicInteger sum = new AtomicInteger(0);
		new Thread(new Producer(sum)).start();
		new Thread(new Consumer(sum)).start();
	}
}
class Producer implements Runnable {
	private AtomicInteger sum;
	public Producer(AtomicInteger sum) {
		this.sum = sum;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
				int n = new Random().nextInt(10)+1;
				int addAndGet = sum.addAndGet(n);
				System.out.println("生产者生产：" + n +"，目前库存总数：" + addAndGet);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class Consumer implements Runnable {
	private AtomicInteger sum;
	public Consumer(AtomicInteger sum) {
		this.sum = sum;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);
				int n = new Random().nextInt(10)+1;
				if (sum.get() > n) {
					int addAndGet = sum.addAndGet(-n);
					System.out.println("消费者消费：" + n + "，目前库存总数：" + addAndGet);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}
