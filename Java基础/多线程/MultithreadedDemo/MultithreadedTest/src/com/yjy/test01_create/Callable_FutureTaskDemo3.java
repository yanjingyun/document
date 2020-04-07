package com.yjy.test01_create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 多线程实现计算总数：1+2+...+2000=?
 */
public class Callable_FutureTaskDemo3 {
	static class CallableDemo implements Callable<Integer> {

		private int begin;
		private int end;
		public CallableDemo(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
		
		@Override
		public Integer call() throws Exception {
			System.out.println(Thread.currentThread().getName() + "开始计算：begin=" + begin + "，end=" + end);
			int sum = 0;
			for (int i = begin; i <= end; i++) {
				sum += i;
			}
			System.out.println(Thread.currentThread().getName() + "结束计算：begin=" + begin + "，end=" + end);
			return sum;
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableDemo demo1 = new CallableDemo(0, 1000);
		CallableDemo demo2 = new CallableDemo(1000, 2000);
		
		FutureTask<Integer> task1 = new FutureTask<Integer>(demo1);
		FutureTask<Integer> task2 = new FutureTask<Integer>(demo2);
		new Thread(task1).start();
		new Thread(task2).start();
		
		int sum1 = task1.get();
		int sum2 = task2.get();
		System.out.println("sum1=" + sum1);
		System.out.println("sum2=" + sum2);
		System.out.println("sum=" + (sum1+ sum2));
	}
}
