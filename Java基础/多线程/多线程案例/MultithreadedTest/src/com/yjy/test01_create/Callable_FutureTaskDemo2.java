package com.yjy.test01_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callable_FutureTaskDemo2 {

	static class Task implements Callable<Integer> {
		@Override
		public Integer call() throws Exception {
			System.out.println("子线程在执行任务...");
			Thread.sleep(3000);
			return 1000;
		}
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Task task = new Task();
		Future<Integer> future = executorService.submit(task);
		executorService.shutdown();
		System.out.println("主线程在执行任务...");

		Thread.sleep(2000); //模拟任务消耗时间
		System.out.println("task运行结果，future:" + future.get());
		System.out.println("所有任务执行完毕");
	}
}
