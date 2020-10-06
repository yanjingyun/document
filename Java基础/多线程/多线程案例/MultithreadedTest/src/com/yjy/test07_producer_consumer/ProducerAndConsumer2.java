package com.yjy.test07_producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 模拟生产者和消费者场景：ArrayBlockingQueue实现
 */
public class ProducerAndConsumer2 {

	// 定义阻塞队列大小
	private static final int maxSize = 5;

	public static void main(String[] args) {
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(maxSize);
		new Thread(new Productor(queue)).start();
		new Thread(new Customer(queue)).start();
	}

	static class Productor implements Runnable {
		private BlockingQueue<Integer> queue;
		private int count = 1;

		Productor(BlockingQueue<Integer> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					queue.put(count);
					System.out.println("生产者正在生产第" + count + "个商品");
					count++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Customer implements Runnable {
		private BlockingQueue<Integer> queue;

		Customer(BlockingQueue<Integer> queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					int count = (int) queue.take();
					System.out.println("customer正在消费第" + count + "个商品===");
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
