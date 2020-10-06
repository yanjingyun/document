package com.yjy.test10_threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest1 {
	
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
		// 5核心，10最大，5-队列
		ThreadPoolExecutor exec = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MICROSECONDS,
				new ArrayBlockingQueue<Runnable>(5));
		for (int i = 0; i < 15; i++) {
			exec.execute(new MyTask(i));
			System.out.println("线程池中线程数" + exec.getPoolSize() + "，等待队列线程数：" + exec.getQueue().size() + "，已执行完的任务数："
					+ exec.getCompletedTaskCount());
		}
		exec.shutdown();
	}

}
