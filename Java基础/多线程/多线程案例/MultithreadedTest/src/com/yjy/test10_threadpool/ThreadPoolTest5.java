package com.yjy.test10_threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest5 {

	static class MyTask implements Runnable {
		private int taskNum;

		public MyTask(int i) {
			taskNum = i;
		}

		@Override
		public void run() {
			String name = Thread.currentThread().getName();
			System.out.println("线程：" + name + " 执行" + taskNum);
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("线程" + name + "执行完毕" + taskNum);
		}
	}

	public static void main(String[] args) {
		// 自定义拒绝策略
		RejectedExecutionHandler myRejectedExecutionHandler = new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				// 记录异常，报警处理等
				// 处理：将这些任务保存到数据库中，等下次在进行处理
				System.out.println("接收不了那么多请求，自定义拒绝策略...");
			}
		};
		// 5核心，10最大，5-队列
		ThreadPoolExecutor exec = new ThreadPoolExecutor(3, 6, 200, TimeUnit.MICROSECONDS,
				new ArrayBlockingQueue<Runnable>(5), myRejectedExecutionHandler);
		for (int i = 0; i < 35; i++) {
			exec.execute(new MyTask(i));
		}
		exec.shutdown();
	}

}
