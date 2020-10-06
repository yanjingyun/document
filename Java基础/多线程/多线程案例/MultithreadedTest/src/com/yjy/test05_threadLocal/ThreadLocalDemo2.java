package com.yjy.test05_threadLocal;

public class ThreadLocalDemo2 {

	// 1.创建银行对象：钱、取款、存款
	static class Bank {
		private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
			protected Integer initialValue() {
				return 0;
			}
		};

		public Integer get() {
			return threadLocal.get();
		}

		public void set(Integer value) {
			threadLocal.set(threadLocal.get() + value);
		}
	}

	// 2.创建转账对象；从银行中取钱，转账，保存到账户
	static class Transfer implements Runnable {
		private Bank bank;

		public Transfer(Bank bank) {
			this.bank = bank;
		}

		public void run() {
			for (int i = 0; i < 10; i++) {
				bank.set(10);
				System.out.println(Thread.currentThread().getName() + "账户余额：" + bank.get());
			}
		}
	}

	// 3.在main方法中使用两个对象模拟转账
	public static void main(String[] args) {
		Bank bank = new Bank();
		Transfer transfer = new Transfer(bank);
		Thread t1 = new Thread(transfer);
		Thread t2 = new Thread(transfer);
		t1.start();
		t2.start();
	}
}
