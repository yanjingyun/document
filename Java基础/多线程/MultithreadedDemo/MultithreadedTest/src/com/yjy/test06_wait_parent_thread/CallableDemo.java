package com.yjy.test06_wait_parent_thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableDemo {
	public static void main(String[] args) {
		Callable<Integer> callable = new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				String name = Thread.currentThread().getName();
				System.out.println(name + "开始执行");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(name + "执行完毕...");
				return 0;
			}
		};
		
		FutureTask<Integer> task1 = new FutureTask<Integer>(callable);
		FutureTask<Integer> task2 = new FutureTask<Integer>(callable);
		System.out.println("主线程开始执行");
		new Thread(task1, "线程A").start();
		new Thread(task2, "线程B").start();
		while (true) {
			if (task1.isDone() && task2.isDone()) {
				System.out.println("主线程执行完毕...");
				break;
			}
		}
	}
}
