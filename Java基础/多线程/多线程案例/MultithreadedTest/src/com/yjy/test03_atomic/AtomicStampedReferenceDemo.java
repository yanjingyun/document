package com.yjy.test03_atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference解决cas的ABA问题
 */
public class AtomicStampedReferenceDemo {
	static AtomicStampedReference<Integer> atomicInteger;
	public static void main(String[] args) throws InterruptedException {
		int j = 0;
		while (j < 100) {
			atomicInteger = new AtomicStampedReference<Integer>(0, 0);
			Thread t1 = new Thread() {
				public void run() {
					int stamp;
					Integer reference;
					for (int i = 0; i < 1000; i++) {
						do {
							stamp = atomicInteger.getStamp();
							reference = atomicInteger.getReference();
						} while (!atomicInteger.compareAndSet(reference, reference+1, stamp, stamp+1));
					}
				}
			};
			Thread t2 = new Thread() {
				public void run() {
					int stamp;
					Integer reference;
					for (int i = 0; i < 1000; i++) {
						do {
							stamp = atomicInteger.getStamp();
							reference = atomicInteger.getReference();
						} while (!atomicInteger.compareAndSet(reference, reference+1, stamp, stamp+1));
					}
				}
			};
			
			t1.start();
			t2.start();
			t1.join();
			t2.join();
			
			System.out.println("最终值n=" + atomicInteger.getReference());
			j++;
		} 
	}
}
