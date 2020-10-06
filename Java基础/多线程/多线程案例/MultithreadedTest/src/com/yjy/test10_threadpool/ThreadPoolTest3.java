package com.yjy.test10_threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest3 {
	public static void main(String[] args) {
		// newCachedThreadPool:创建100个线程执行100个任务（由于任务执行时间较长那个，线程池只能创建新的线程去执行，若把sleep去掉，会看到线程能够被复用）
//		test1();

//		// newFixedThreadPool:创建固定10个线程去执行100个任务
//		test2();
		
//		// newSingleThreadExecutor：创建一个线程去执行100个任务
//		test3();

		

//		// ThreadPoolExecutor：自定义先乘除，到了31个任务时会抛异常（10个核心线程+10个非核心线程数+10个任务放队列）
//		test4();
		
//		// 使用多线程执行任务，线程池的线程数已满，拒绝策略（由调用线程处理该任务 ）
//		test5();

	}

	// 创建100个线程执行100个任务（由于任务执行时间较长那个，线程池只能创建新的线程去执行，若把sleep去掉，会看到线程能够被复用）
	public static void test1() {
		ExecutorService e1 = Executors.newCachedThreadPool();
		for (int i = 1; i <= 100; i++) {
			e1.execute(new MyTask(i));
		}
	}

	// 创建固定10个线程去执行100个任务
	public static void test2() {
		ExecutorService e2 = Executors.newFixedThreadPool(10);
		for (int i = 1; i <= 100; i++) {
			e2.execute(new MyTask(i));
		}
	}

	// 创建固定10个线程去执行100个任务
	public static void test3() {
		ExecutorService e3 = Executors.newSingleThreadExecutor();
		for (int i = 1; i <= 100; i++) {
			e3.execute(new MyTask(i));
		}
	}
	
	// 到了31个任务时会抛异常（10个核心线程+10个最大线程数+10个任务放队列）
	public static void test4() {
		ThreadPoolExecutor t = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
		for (int i = 1; i <= 100; i++) {
			t.execute(new MyTask(i));
		}
	}
	
	// 使用多线程执行任务，线程池的线程数已满，拒绝策略（由调用线程处理该任务 ）
	public static void test5() {
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10,10,0, TimeUnit.SECONDS,new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());
		for (int i = 0; i < 1000; i++) {
			final int ii = i;
			threadPool.execute(() -> {
				System.out.println(Thread.currentThread().getName() + " :::: " + ii);
			});
		}
	}

	static class MyTask implements Runnable {
		int i;

		public MyTask(int i) {
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "------" + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
