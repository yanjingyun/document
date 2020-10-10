package com.yjy.test07_producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 模拟多个生产者和多个消费者：ArrayBlockingQueue实现
 */
public class ProducerAndConsumer {

	static class Producer implements Runnable {
		private BlockingQueue<Integer> messageQueue;

		public Producer(BlockingQueue<Integer> messageQueue) {
			this.messageQueue = messageQueue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(500);
					int n = new Random().nextInt(10) + 1;
					System.out.println(Thread.currentThread().getName() + "生产产品数：" + n);
					messageQueue.put(n);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	static class Consumer implements Runnable {
		private BlockingQueue<Integer> messageQueue;

		public Consumer(BlockingQueue<Integer> messageQueue) {
			this.messageQueue = messageQueue;
		}

		@Override
		public void run() {
			while (true) {
				try {
					if (!messageQueue.isEmpty()) {
						Thread.sleep(500);
						Integer take = messageQueue.take();
						System.out.println(Thread.currentThread().getName() + "消费产品数：" + take);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		BlockingQueue<Integer> messageQueue = new ArrayBlockingQueue<Integer>(10);
		// 模拟一个生成者和两个消费者
		new Thread(new Producer(messageQueue)).start();
		new Thread(new Consumer(messageQueue)).start();
		new Thread(new Consumer(messageQueue)).start();
	}
}
