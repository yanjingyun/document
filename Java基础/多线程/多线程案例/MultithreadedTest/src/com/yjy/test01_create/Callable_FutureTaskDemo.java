package com.yjy.test01_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class Callable_FutureTaskDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
			public Integer call() throws Exception {
				int sum = 0;
				for (int i = 0; i <= 10; i++) {
					System.out.println(Thread.currentThread().getName() + "::" + i);
					sum += i;
				}
				Thread.sleep(2000);
				return sum;
			}
		});
		
		// 创建线程方式执行任务
//		Thread thread = new Thread(futureTask);
//		thread.start();
//		System.out.println("结果为：" + futureTask.get());
		
		// 线程池方式执行任务
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.execute(futureTask);
		System.out.println("结果为：" + futureTask.get());
	}
}
