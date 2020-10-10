package com.yjy.test04_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

	/**
	 * 读写锁操作Map
	 */
	static class OperatorMap {
		private Map<String, String> map = new HashMap<>(); // 操作的map对象

		private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock(); // 读操作锁
		private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock(); // 写操作锁

		public String get(String key) {
			String name = Thread.currentThread().getName();
			readLock.lock();
			try {
				System.out.println(name + "读操作已加锁...");
				Thread.sleep(3000);
				System.out.println(name + "执行set操作：key=" + key + "，value=" + map.get(key));
				return map.get(key);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(name + "读操作已解锁...");
				readLock.unlock();
			}
			return null;
		}

		public void put(String key, String value) {
			String name = Thread.currentThread().getName();
			writeLock.lock();
			try {
				System.out.println(name + "写操作已加锁...");
				Thread.sleep(2000);
				map.put(key, value);
				System.out.println(name + "执行get操作：key=" + key + "，value=" + map.get(key));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				System.out.println(name + "写操作已解锁...");
				writeLock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		OperatorMap map = new OperatorMap();
		map.put("key1", "value1"); //main线程写操作
		new Thread(() -> map.get("key1"), "读线程1").start();
		new Thread(() -> map.get("key1"), "读线程2").start();
		
		new Thread(() -> map.put("key1", "写线程1"), "写线程1").start();
		new Thread(() -> map.put("key1", "写线程2"), "写线程2").start();
		
		new Thread(() -> map.get("key1"), "读线程3").start();
		new Thread(() -> map.get("key1"), "读线程4").start();
	}
}
