package com.yjy.test10_threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 模拟线程池处理计算型任务
 */
public class ThreadPoolTest4 {

	private static int corePoolSize = Runtime.getRuntime().availableProcessors();
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, corePoolSize + 1, 60,
			TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(500));

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		List<Future<Integer>> futures = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			final int index = i;
			Future<Integer> future = executor.submit((Callable<Integer>) () -> {
				if (index == 4 || index == 22) { // 假装这个计算执行时间过长
					Thread.sleep(4000);
				}
				return index + index; // 模拟计算
			});
			
			futures.add(future);
		}

		for (Future<Integer> future : futures) {
			try {
//				Integer integer = future.get(); // 获取结果
				Integer integer = future.get(1000, TimeUnit.MILLISECONDS); // 获取结果，设置等待超时时间
				System.out.println("任务i=" + integer + "获取完成!");
			} catch (Exception e) {
				System.out.println("处理任务失败" + e.getMessage());
			}
		}
	}
}
