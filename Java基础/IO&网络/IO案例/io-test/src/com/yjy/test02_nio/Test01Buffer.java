package com.yjy.test02_nio;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 一、缓冲区（Buffer）：在JavaNIO中负责数据的存取。缓冲区就是数组，用于存储不同数据类型的数据
 * 
 * 根据数据类型不同（boolean除外），提供了相应类型的缓冲区：ByteBuffer、CharBuffer、Shortbuffer、IntBuffer、LongBuffer、FloatBuffer、DoubleBuffer
 * 
 * 上述缓冲区的管理方式几乎一致，通过 allocate() 获取缓冲区
 * 二、缓冲区存取数据的两个核心方法：
 * put() : 存入数据到缓冲区中
 * get() : 获取缓冲区中的数据
 * 
 * 三、缓冲区(java.nio.Buffer)中的四个核心属性：
 * capacity:容量，表示缓冲区中最大存储数据的容量。一旦声明不能改变
 * limit:界限，表示缓冲区中可以操作数据的大小，（limit后面的数据不能进行读写）
 * position:位置，表示缓冲区中正在操作数据的位置
 * 
 * mark:标记，表示记录当前position的位置，可以通过reset() 恢复到mark的位置
 * 
 * 0 <= mark <=position <= limit <= capacity
 * 
 * 四、直接缓冲区与非直接缓冲区
 * 非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中，可以提高效率
 * 
 * 非直接缓冲区：当我们应用程序发起一个读请求，首先会先把物理磁盘的这些数据读取到内核地址空间中，读到缓存中，然后将内核地址的数据copy到用户地址空间（说白了就是jvm内存），然后再把数据读到我们应用程序中来。
 */
public class Test01Buffer {
	
	/** capacity，limit，position */
	@Test
	public void test1111() {
		// 1.分配一个指定大小的缓冲区：allocate(10)
		ByteBuffer buf = ByteBuffer.allocate(10);
		// capacity:10，position：0，limit：10
		System.out.println(String.format("allocate(10) >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
		
		// 2.写数据：将数据存入缓冲区中 put()
		buf.put("abcde".getBytes());
		// capacity:10，position：5，limit：10
		System.out.println(String.format("put() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 3.切换为读数据 flip()
		buf.flip();
		// capacity:10，position：0，limit：5
		System.out.println(String.format("flip() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 4.读数据：读取缓冲区的数据 get()
		// capacity:10，position：5，limit：5
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println(new String(dst, 0, dst.length));
		System.out.println(String.format("get() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 5.rewind():可重复读
		// capacity:10，position：0，limit：5
		buf.rewind();
		System.out.println(String.format("rewind() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));

		// 6.clear()：清空缓冲区，但缓冲区中的数据仍然存在
		// capacity:10，position：0，limit：10
		buf.clear();
		System.out.println(String.format("clear() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
	}
	
	/** mark */
	@Test
	public void test2() {
		String str = "abcde";
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.put(str.getBytes());
		System.out.println(String.format("put() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
		buf.flip();
		
		byte[] dst = new byte[buf.limit()];
		buf.get(dst, 0, 2);
		System.out.println(new String(dst, 0, 2));
		System.out.println(String.format("get() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
		
		// mark():标记
		buf.mark();
		buf.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println(String.format("mark() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
		
		// reset():恢复到mark的位置
		buf.reset();
		System.out.println(String.format("reset() >> capacity:%s，position：%s，limit：%s", buf.capacity(), buf.position(), buf.limit()));
		
		// 判断缓冲区是否还有剩余数据
		if (buf.hasRemaining()) {
			// 获取缓冲区中可以操作的数量
			System.out.println(buf.remaining());
		}
	}
	
	@Test
	public void test3() {
		// 分配直接缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		System.out.println(buffer.isDirect());
	}
}
