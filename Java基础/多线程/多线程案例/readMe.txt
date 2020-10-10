
com.yjy.test1_create //3种创建线程形式
	1.继承Thread类，重写run方法(ThreadDemo.java)
	2.实现Runnable接口，将Runnable对象传入Thread对象中(RunnableDemo.java)
	3.实现Callable接口，将Callable对象传入FutureTask对象，再将Future对象传入Thread对象中
		Callable_FutureTaskDemo.java
		Callable_FutureTaskDemo2.java

com.yjy.test2_thread_communication //线程间通信机制
	1.Synchronied关键字的等待/通知机制
		Synchronized_Object_Demo：测试synchronized实现线程通知
		Synchronized_Object_Demo2：实现线程交替打印1~10数字
	2.Condition条件对象的等待/通知机制
		Lock_Condition_Demo：测试Lock实现线程通知
		Lock_Condition_Demo2：实现线程交替打印1~10数字
	3.同步计数器CountDownLatch实现
		CountDownLatchDemo：教练训练运动员，教练需要等待所有运动员就位才开始，若等待超时则直接开始
	4.循环屏障CycilcBarrier实现
		CyclicBarrierDemo：三个线程同时执行，需要等待所有线程到达后，才往下执行
	5.信号量Semaphore实现
		SemaphoreDemo：模拟8个工人使用3台机器工作，1台机器同一时间只能1人操作

com.yjy.test3_atomic
	1.AtomicStampedReference解决cas的ABA问题(AtomicStampedReferenceDemo.java)

com.yjy.test4_lock
	分类(15种)：公平锁/非公平锁、可重入锁/不可重入锁、独享锁/共享锁、互斥锁/读写锁、悲观锁/乐观锁、分段锁、偏向锁/轻量级锁/重量级锁、自旋锁
	1.ReentrantLock测试(ReentrantLockDemo.java)
	2.ReadWriteLock测试-读写锁操作map(ReadWriteLockDemo.java)

com.yjy.test5_threadLocal
	1.ThreadLocal测试
		ThreadLocalDemo：统计某个线程执行所用时间
		ThreadLocalDemo2：模拟银行转账操作
	2.SubAndThreadLocalTest：子线程不能拿到父线程的ThreadLocal值
	3.数据库连接中，Connection对象是单例。该对象需共享，但使用同步方法保证线程安全，但会造成另外一线程等待。使用ThreadLocal既可以保证线程安全又可以保证性能。(buildSession.txt)

com.yjy.test6_wait_parent_thread //测试让主线程等待子线程执行完后再执行
	描述：现主线程X，和两个子线程A和B，主线程X需要在线程A和B执行完成之后再执行，线程A和线程B的执行顺序可不一致。
	1.使用join方法。(JoinThreadDemo.java)
	2.使用同步计数器CountDownLatch。(CountDownLatchDemo.java)
	3.使用Callable和FutureTask。(CallableDemo.java)

com.yjy.test07_producer_consumer //测试生成者和消费者模式
	1.一个生产者和一个消费者，规定生产一只烤鸭后才能消费一只烤鸭，使用wait和notify进行线程等待/通知机制。
	2.多个生产者和多个消费者测试：使用BlockingQueue存储商品(ProducerAndConsumer.java)
		模拟多个生产者和多个消费者：ArrayBlockingQueue实现
		模拟生产者和消费者场景：ArrayBlockingQueue实现
		模拟生产者和消费者场景：AtomicInteger实现

com.yjy.test08_dead_lock //测试死锁
	1.测试死锁(DeadLockDemo.java)


com.yjy.test09_countdownlatch //测试CountDownLatch同步类
	CountDownLatchDemo //有5000个任务需要处理，我们可以分5个子任务
	com.yjy.test02_communication.CountDownLatchDemo //教练训练运动员，教练需要等待所有运动员就位才开始
	Customer_Waiter_OrderDishes //顾客点菜
		张三、李四、王五约好一起去吃饭，等到所有人都到齐了才让服务员上菜；若有一名顾客迟迟未到，饭店都打烊了，因此服务员不能一直等，会设置等待时间。


com.yjy.test10_threadpool	//测试线程池ThreadPoolExecutor类
	ThreadPoolTest1	--测试ThreadPoolExecutor使用（当线程池中线程额数目大于5时，便将任务放入到任务缓存队列中，当任务缓存队列满了之后，便创建新的线程。如果上面程序中，将for循环中改成执行20个任务，就会抛出任务拒绝异常了）
	ThreadPoolTest2 --测试CPU密集型任务的线程池设置
	ThreadPoolTest3	--测试Executors类提供的4种内置线程池
	ThreadPoolTest4	--模拟计算型任务的线程池设置
	ThreadPoolTest5	--自定义拒绝策略，将这些任务放到数据库中，等下次再处理











-------------------------分割线-------------------------
com.yjy.test99_other：测试其它小demo
	Test01.java //测试Synchronized里使用wait/notify时的执行顺序
	Test02.java	//测试三个线程交替打印1~1000
		参考com.yjy.test02_communication.Synchronized_Object_Demo2，两线程交替打印1~10
