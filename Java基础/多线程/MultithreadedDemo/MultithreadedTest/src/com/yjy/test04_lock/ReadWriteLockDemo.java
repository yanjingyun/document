package com.yjy.test04_lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
	private Map<String, String> map = new HashMap<>(); //操作的map对象
	
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock(); //读操作锁
	private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock(); //写操作锁
	
	public String get(String key) {
		readLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "读操作已加锁...");
			Thread.sleep(3000);
			return map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + "读操作已解锁...");
			readLock.unlock();
		}
		return null;
	}
	
	public void put(String key, String value) {
		writeLock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + "写操作已加锁...");
			Thread.sleep(3000);
			map.put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println(Thread.currentThread().getName() + "写操作已解锁...");
			writeLock.unlock();
		}
	}
	
	public static void main(String[] args) {
		ReadWriteLockDemo demo = new ReadWriteLockDemo();
		demo.put("key1", "value1");
		new Thread("读线程1") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
		new Thread("读线程2") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
		
		new Thread("写线程1") {
			public void run() {
				demo.put("key1", "写线程1");
			}
		}.start();
		new Thread("写线程2") {
			public void run() {
				demo.put("key1", "写线程2");
			}
		}.start();
		
		new Thread("读线程3") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
		new Thread("读线程4") {
			public void run() {
				System.out.println("key1=" + demo.get("key1"));
			}
		}.start();
	}
}
