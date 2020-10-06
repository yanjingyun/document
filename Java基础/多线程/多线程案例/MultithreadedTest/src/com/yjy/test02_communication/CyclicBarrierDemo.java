package com.yjy.test02_communication;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
	private CyclicBarrier cyclicBarrier = new CyclicBarrier(3); // 参与CyclicBarrier的线程数为3

	public void startThread() {
		// 1.打印线程准备启动
		String name = Thread.currentThread().getName();
		System.out.println(name + "正在准备...");
		// 2.调用CyclicBarrier的await方法等待线程全部准备完成
		try {
			cyclicBarrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
		// 3.打印线程启动完毕信息
		System.out.println(name + "已经启动完毕，time：" + System.currentTimeMillis());
	}

	public static void main(String[] args) {
		CyclicBarrierDemo demo = new CyclicBarrierDemo();
		new Thread(new Runnable() {
			public void run() {
				demo.startThread();
			}
		}, "线程1").start();
		new Thread(new Runnable() {
			public void run() {
				demo.startThread();
			}
		}, "线程2").start();
		new Thread(new Runnable() {
			public void run() {
				demo.startThread();
			}
		}, "线程3").start();
	}

}
