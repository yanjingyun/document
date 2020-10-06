package com.yjy.test10_threadpool;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest2 {
	/**
	 * 1）IO密集型任务 = 一般为2*CPU核心数（常出现于线程中：数据库数据交互、文件上传下载、网络数据传输等等） 
	 * 2）CPU密集型任务 = 一般为CPU核心数+1（常出现于线程中：复杂算法）
	 * 3）混合型任务 = 视机器配置和复杂度自测而定
	 */
	private static int corePoolSize = Runtime.getRuntime().availableProcessors();
	
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 10l,
			TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
	
	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(5);
		// 使用execute方法
		executor.execute(new MyTask("任务A", 1000, latch));
		executor.execute(new MyTask("任务B", 1000, latch));
		executor.execute(new MyTask("任务C", 1000, latch));
		executor.execute(new MyTask("任务D", 1000, latch));
		executor.execute(new MyTask("任务E", 1000, latch));
		
		System.out.println("线程池中线程数" + executor.getPoolSize() + "，等待队列线程数：" + executor.getQueue().size() + "，已执行完的任务数："
				+ executor.getCompletedTaskCount());
		
		try {
			latch.await(); // 等待所有人任务结束
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("所有的统计任务执行完成:" + LocalDateTime.now());
	}

	static class MyTask implements Runnable {
		String statsName;
		int runTime;
		CountDownLatch latch;

		public MyTask(String statsName, int runTime, CountDownLatch latch) {
			this.statsName = statsName;
			this.runTime = runTime;
			this.latch = latch;
		}

		public void run() {
			try {
				System.out.println(statsName + " 开始执行 " + LocalDateTime.now());
				// 模拟任务执行时间
				Thread.sleep(runTime);
				System.out.println(statsName + " 执行完毕 " + LocalDateTime.now());
				latch.countDown();// 单次任务结束，计数器减一
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
