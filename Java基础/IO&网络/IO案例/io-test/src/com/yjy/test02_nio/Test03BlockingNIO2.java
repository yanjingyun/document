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

// 测试：先开启一个server，再开启两个client
public class Test03BlockingNIO2 {
	
	
	/** 客户端 */
	@Test
	public void client() throws IOException {
		// 1.获取通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8080));
	
		// 2.分配指定大小的缓冲区
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		// 3.读取本地文件，并发送到服务端
		FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
		while (inChannel.read(buf) != -1) {
			buf.flip();
			sChannel.write(buf);
			buf.clear();
		}
		
		// 在不关闭通道的情况下关闭写入连接
		sChannel.shutdownOutput();
		
		// 4.接收服务端的反馈
		int len = 0;
		while ((len = sChannel.read(buf)) > 0) {
			buf.flip();
			System.out.println(new String(buf.array(), 0, len));
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
		
		// 发送反馈给客户端
		buf.put("服务端接收数据成功！".getBytes());
		buf.flip();
		sChannel.write(buf);
		
		// 6.关闭通道
		outChannel.close();
		sChannel.close();
		ssChannel.close();
	}
}
