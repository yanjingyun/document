
com.yjy.test1_create //3种创建线程形式
	1.继承Thread类，重写run方法(ThreadDemo.java)
	2.实现Runnable接口，将Runnable对象传入Thread对象中(RunnableDemo.java)
	3.实现Callable接口，将Callable对象传入FutureTask对象，再将Future对象传入Thread对象中(Callable_FutureTaskDemo.java || Callable_FutureTaskDemo2.java)

com.yjy.test2_thread_communication //线程间通信机制
	1.Synchronied关键字的等待/通知机制(Synchronized_Object_Demo.java || Synchronized_Object_Demo2.java)
	2.Condition条件对象的等待/通知机制(Lock_Condition_Demo.java || Lock_Condition_Demo2.java)
	3.同步计数器CountDownLatch实现(CountDownLatchDemo.java)
	4.循环屏障CycilcBarrier实现(CyclicBarrierDemo.java)
	5.信号量Semaphore实现(SemaphoreDemo.java)

com.yjy.test3_atomic
	1.AtomicStampedReference解决cas的ABA问题(AtomicStampedReferenceDemo.java)

com.yjy.test4_lock
	分类(15种)：公平锁/非公平锁、可重入锁/不可重入锁、独享锁/共享锁、互斥锁/读写锁、悲观锁/乐观锁、分段锁、偏向锁/轻量级锁/重量级锁、自旋锁
	1.ReentrantLock测试(ReentrantLockDemo.java)
	2.ReadWriteLock测试(ReadWriteLockDemo.java)

com.yjy.test5_threadLocal
	1.ThreadLocal测试(ThreadLocalDemo.java || ThreadLocalDemo2.java)
	2.子线程是否能拿到父线程的私有属性(SubAndThreadLocalTest.java)
	3.数据库连接中，Connection对象是单例。该对象需共享，但使用同步方法保证线程安全，但会造成另外一线程等待。使用ThreadLocal既可以保证线程安全又可以保证性能。(buildSession.txt)

com.yjy.test6_wait_parent_thread //测试让主线程等待子线程执行完后再执行
	描述：现主线程X，和两个子线程A和B，主线程X需要在线程A和B执行完成之后再执行，线程A和线程B的执行顺序可不一致。
	1.使用join方法。(JoinThreadDemo.java)
	2.使用同步计数器CountDownLatch。(CountDownLatchDemo.java)
	3.使用Callable和FutureTask。(CallableDemo.java)

com.yjy.test07_producer_consumer //测试生成者和消费者模式
	1.一个生产者和一个消费者，规定生产一只烤鸭后才能消费一只烤鸭，使用wait和notify进行线程等待/通知机制。
	2.多个生产者和多个消费者测试：使用BlockingQueue存储商品(ProducerAndConsumer.java)

com.yjy.test08_dead_lock //测试死锁
	1.测试死锁(DeadLockDemo.java)












-------------------------分割线-------------------------
com.yjy.test99_other：测试其它小demo
	Test01.java //测试Synchronized里使用wait/notify时的执行顺序
