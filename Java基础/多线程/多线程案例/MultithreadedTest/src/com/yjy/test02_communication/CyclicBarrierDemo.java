package com.yjy.test02_communication;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 测试CyclicBarrier：等待所有线程到达了，才往下执行
 */
public class CyclicBarrierDemo {
	
	static class EatFood implements Runnable {
		
		private CyclicBarrier cyclicBarrier;
		public EatFood(CyclicBarrier cyclicBarrier) {
			this.cyclicBarrier = cyclicBarrier;
		}
		
		@Override
		public void run() {
			// 1.打印线程准备启动
			String name = Thread.currentThread().getName();
			long start = System.currentTimeMillis();
			try {
				Random random = new Random();
				int sleepTime = random.nextInt(2000) + 1000;
				System.out.println(name + "正在准备，准备时间为" + sleepTime + "ms");
				Thread.sleep(sleepTime);
				
				// 2.调用CyclicBarrier的await方法等待线程全部准备完成
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			// 3.打印线程启动完毕信息
			System.out.println(name + "已经启动完毕，总执行时间：" + (System.currentTimeMillis() - start));
		}
	}

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		EatFood EatFood = new EatFood(cyclicBarrier);
		new Thread(EatFood, "线程1").start();
		new Thread(EatFood, "线程2").start();
		new Thread(EatFood, "线程3").start();
	}

}
