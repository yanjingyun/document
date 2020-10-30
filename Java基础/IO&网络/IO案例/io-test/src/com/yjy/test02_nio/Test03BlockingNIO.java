package com.yjy.test02_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * 一、使用NIO完成网路通信的三个核心：
 * 1.通道（Channel）：负责连接
 * 		java.nio.channels.Channel接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 * 
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 * 
 * 2.缓冲区（Buffer）：负责数据的存取
 * 
 * 3.选择器（Selector）：是SelectableChannel的多路复用器。用于监控SelectableChannel的IO状况
 *
 */
// 测试：先开启一个server，再开启两个client
public class Test03BlockingNIO {
	
	
	/** 客户端 */
	@Test
	public void client() throws IOException {
		// 1.获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
	
		// 2.分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		// 3.读取本地文件，并发送到服务端
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		while (inChannel.read(buf) > 0) {
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		// 4.关闭通道
		inChannel.close();
		sChannel.close();
	}
	
	/** 服务端 */
	@Test
	public void server() throws IOException {
		// 1.获取通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		
		// 2.绑定连接
		ssChannel.bind(new InetSocketAddress(8080));
		
		// 3.获取客户端连接的通道
		SocketChannel sChannel = ssChannel.accept();
		
		// 4.分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		// 5.接收客户端的数据，并保存到本地
		FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		while (sChannel.read(buf) > 0) {
			buf.flip();
			outChannel.write(buf);
			buf.clear();
		}
		
		// 6.关闭通道
		outChannel.close();
		sChannel.close();
		ssChannel.close();
	}
}
