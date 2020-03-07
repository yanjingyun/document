package com.yjy.io;

import java.nio.ByteBuffer;

public class BufferTest {
	public static void main(String[] args) {
		// 1.分配一个指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		System.out.println("-------------------allocate()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 2.写数据：将数据存入缓冲区中
		buf.put("abcd".getBytes());
		System.out.println("-------------------put()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 3.切换为读数据
		buf.flip();
		System.out.println("-------------------flip()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 4.读数据：读取缓冲区的数据
		byte[] dst = new byte[buf.limit()];
		buf.get(dst);
		System.out.println(new String(dst, 0, dst.length));
		System.out.println("-------------------get()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 5.rewind():可重复读
		buf.rewind();
		System.out.println("-------------------rewind()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
		
		// 6.clear()：清空缓冲区，但缓冲区中的数据仍然存在
		buf.clear();
		System.out.println("-------------------clear()-------------------");
		System.out.println(buf.capacity());
		System.out.println(buf.position());
		System.out.println(buf.limit());
	}
}
