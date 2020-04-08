package com.yjy.test01_string;

public class Test06 {

	public static Test06 t1 = new Test06();
	public static Test06 t2 = new Test06();

	{
		System.out.println("构造块");
	}
	static {
		System.out.println("静态块");
	}

	public static void main(String[] args) {
		Test06 t = new Test06();
	}
}
// 输出：构造块 构造块 静态块 构造块
// 说明：开始时JVM加载Test06.class，对所有的静态成员进行声明，t1、t2被初始化为默认值，为null，又因为t1、t2需要被显式初始化，所以对t1进行显式初始化，初始化代码块→构造函数（没有就是调用默认的构造函数），咦！静态代码块咋不初始化？因为在开始时已经对static部分进行了初始化，虽然只对static变量进行了初始化，但在初始化t1时也不会再执行static块了，因为JVM认为这是第二次加载类B了，所以static会在t1初始化时被忽略掉，所以直接初始化非static部分，也就是构造块部分（输出''构造块''）接着构造函数（无输出）。接着对t2进行初始化过程同t1相同（输出'构造块'），此时就对所有的static变量都完成了初始化，接着就执行static块部分（输出'静态块'），接着执行，main方法，同样也，new了对象，调用构造函数输出（'构造块'）
