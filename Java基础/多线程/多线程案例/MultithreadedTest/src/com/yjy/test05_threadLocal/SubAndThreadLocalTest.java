package com.yjy.test05_threadLocal;

/**
 * 测试：线程内创建子线程，子线程如何拿到父线程的私有属性
 */
public class SubAndThreadLocalTest {
	/**
	 * 输出：
	 * 	父线程Thread-0:父线程私有变量
	 * 	子线程Thread-1:null
	 * 结果分析：
	 * 	子线程并不能输出父线程的局部变量
	 */
	public static void main(String[] args) {
		ThreadLocal<String> t1 = new ThreadLocal<String>();
		new Thread(new Runnable() {
			@Override
			public void run() {
				t1.set("父线程私有变量");
				System.out.println("父线程" + Thread.currentThread().getName() + ":" + t1.get());
				new Thread(new Runnable() {
					@Override
					public void run() {
						System.out.println("子线程" + Thread.currentThread().getName() + ":" + t1.get());
					}
					
				}).start();
			}
		}).start();

	}
}
