package com.yjy.test02_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印1~10
 * 使用Lock和Condition实现线程间通信机制
 */
public class Lock_Condition_Demo2 {
	
	public static class PrintNum {
		private int i = 1;
		private int count;
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		
		public PrintNum(int count) {
			this.count = count;
		}
		
		/**
		 * 奇数打印
		 */
		private void printOdd() {
			lock.lock();
			try {
				while (i <= count) {
					if (i % 2 == 1) {
						System.out.println("基数" + i);
						i++;
						condition.signal(); // 唤醒偶数线程打印
					} else {
						try {
							condition.await(); // 等待偶数线程打印
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} finally {
				lock.unlock();
			}
		}
		
		/**
		 * 偶数打印
		 */
		private void printEven() {
			lock.lock();
			try {
				while (i <= count) {
					if (i % 2 == 0) {
						System.out.println("基数" + i);
						i++;
						condition.signal(); // 唤醒偶数线程打印
					} else {
						try {
							condition.await(); // 等待偶数线程打印
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			} finally {
				lock.unlock();
			}
		}
	}
	
	// 奇数打印
	static class OddPrint implements Runnable {
		
		private PrintNum printNum;
		
		public OddPrint(PrintNum printNum) {
			this.printNum = printNum;
		}

		@Override
		public void run() {
			printNum.printOdd();
		}
		
	}
	
	// 奇数打印
	static class EvenPrint implements Runnable {
		
		private PrintNum printNum;
		
		public EvenPrint(PrintNum printNum) {
			this.printNum = printNum;
		}

		@Override
		public void run() {
			printNum.printEven();
		}
	}
	
	
	
	public static void main(String[] args) {
		PrintNum printNum = new PrintNum(10);
		new Thread(new OddPrint(printNum), "奇数线程").start();
		new Thread(new EvenPrint(printNum), "偶数线程").start();
//		new Thread(() -> printNum.printOdd(), "奇数线程").start();
//		new Thread(() -> printNum.printEven(), "偶数线程").start();
	}
}
