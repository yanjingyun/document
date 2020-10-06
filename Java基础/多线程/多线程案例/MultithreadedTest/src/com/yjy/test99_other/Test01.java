package com.yjy.test99_other;
/*
 * 输出：
	Thread 2 sent notify.
	Thread 1 wake up.
	分析：t1执行obj.wait();时已释放了锁，所以t2可以获得锁，这是t1没获取锁因此不能往下执行，需t2执行完释放锁后，t1才有机会往下执行。
 */
public class Test01 {
	public static void main(String[] args) throws Exception {
		final Object obj = new Object();
		Thread t1 = new Thread() {
			public void run() {
				synchronized (obj) {
					try {
						obj.wait();
						System.out.println("Thread 1 wake up.");
					} catch (InterruptedException e) {
					}
				}
			}
		};
		t1.start();
		
		Thread.sleep(1000);// 等待1秒
		
		Thread t2 = new Thread() {
			public void run() {
				synchronized (obj) {
					obj.notifyAll();
					System.out.println("Thread 2 sent notify.");
				}
			}
		};
		t2.start();
	}
}

