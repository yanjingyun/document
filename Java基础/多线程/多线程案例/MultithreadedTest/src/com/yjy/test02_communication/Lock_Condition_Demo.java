package com.yjy.test02_communication;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock_Condition_Demo {
	static class SignalThread extends Thread {
		private final Lock lock;
		private final Condition newCondition;
		public SignalThread(Lock lock, Condition newCondition) {
			this.lock = lock;
			this.newCondition = newCondition;
		}
		
		@Override
		public void run() {
			try {
				lock.lock();
				System.out.println("notify开始，time=" + System.currentTimeMillis());
				
				Thread.sleep(3000); //模拟业务逻辑执行时间
				newCondition.signal();
				
				System.out.println("notify结束，time=" + System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

	static class AwaitThread extends Thread {
		private final Lock lock;
		private final Condition newCondition;
		public AwaitThread(Lock lock, Condition newCondition) {
			this.lock = lock;
			this.newCondition = newCondition;
		}
		
		@Override
		public void run() {
			try {
				lock.lock();
				System.out.println("wait开始，time=" + System.currentTimeMillis());
				newCondition.await();
				System.out.println("wait结束，time=" + System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		Condition newCondition = lock.newCondition();
		AwaitThread t1 = new AwaitThread(lock, newCondition);
		SignalThread t2 = new SignalThread(lock, newCondition);
		t1.start();
		t2.start();
	}
}
