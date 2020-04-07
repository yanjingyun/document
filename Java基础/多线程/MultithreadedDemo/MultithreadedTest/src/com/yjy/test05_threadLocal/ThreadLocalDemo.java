package com.yjy.test05_threadLocal;

import java.util.concurrent.TimeUnit;

/**
 * 统计某个方法的耗时时间
 */
public class ThreadLocalDemo {

	// static final类型，保证该变量不能指向其它对象
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>();

	public static final void start() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}

	public static final long end() {
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}

	public static void main(String[] args) throws Exception {
		// AOP切入
		ThreadLocalDemo.start(); // 执行方法前
		TimeUnit.SECONDS.sleep(2); // 模拟执行方法
		System.out.println(ThreadLocalDemo.end()); // 执行方法后
	}

}
