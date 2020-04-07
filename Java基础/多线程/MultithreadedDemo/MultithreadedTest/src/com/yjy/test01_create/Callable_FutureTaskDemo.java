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
		
		ExecutorService service = Executors.newFixedThreadPool(1);
		service.submit(futureTask);
		
		System.out.println("½á¹ûÎª£º" + futureTask.get());
	}
}
