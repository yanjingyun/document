package com.yjy.test02_communication;

/**
 * 使用Object的wait和notify实现线程间通信机制
 */
public class Synchronized_Object_Demo2 {
	
	public static class PrintNum {
		private int i = 1;
		private int count;
		private Object obj = new Object();
		
		public PrintNum(int count) {
			this.count = count;
		}
		
		/**
		 * 奇数打印
		 */
		private void printOdd() {
			synchronized(obj) {
				while (i <= count) {
					if (i % 2 == 1) {
						System.out.println("基数" + i);
						i++;
						obj.notify(); // 唤醒奇数线程打印
					} else {
						try {
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		/**
		 * 偶数打印
		 */
		private void printEven() {
			synchronized(obj) {
				while (i <= count) {
					if (i % 2 == 0) {
						System.out.println("基数" + i);
						i++;
						obj.notify(); // 唤醒奇数线程打印
					} else {
						try {
							obj.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
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
//		new Thread(new OddPrint(printNum), "奇数线程").start();
//		new Thread(new EvenPrint(printNum), "偶数线程").start();
		new Thread(() -> printNum.printOdd(), "奇数线程").start();
		new Thread(() -> printNum.printEven(), "偶数线程").start();
	}
}
