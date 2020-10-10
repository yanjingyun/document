package com.yjy.test09_countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch非常实用，我们常常将一个比较大的任务进行拆分，然后开启多个线程来执行，等所有线程都执行完后，再往下执行其它操作
 * 如：有5000个任务需要处理，我们可以分5个子任务，1~1000，1001~2000，2001~3000，3001~4000，4001~5000
 */
public class CountDownLatchDemo {
	private static AtomicInteger count = new AtomicInteger(0);
	static class WorkerRunnable implements Runnable {
		private int start;
		private int end;
		private CountDownLatch doneSignal;

	    WorkerRunnable(int start, int end, CountDownLatch doneSignal) {
	    	this.start = start;
	    	this.end = end;
	        this.doneSignal = doneSignal;
	    }

		@Override
		public void run() {
			for (int i = start; i <= end; i++) {
				System.out.println("已消费完任务" + i);
				count.incrementAndGet(); //自增
			}
			doneSignal.countDown();
		}
	}
	public static void main(String[] args) {
		// 假设有5个子任务
		CountDownLatch doneSignal = new CountDownLatch(5);
		
		Executor e = Executors.newFixedThreadPool(8); //线程池
		e.execute(new WorkerRunnable(1, 1000, doneSignal));
		e.execute(new WorkerRunnable(1001, 2000, doneSignal));
		e.execute(new WorkerRunnable(2001, 3000, doneSignal));
		e.execute(new WorkerRunnable(3001, 4000, doneSignal));
		e.execute(new WorkerRunnable(4001, 5000, doneSignal));
		try {
			// 等待所有的任务完成，这个方法才会返回
			doneSignal.await();
			System.out.println(count.get() + "所有任务已执行完毕！");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
}
