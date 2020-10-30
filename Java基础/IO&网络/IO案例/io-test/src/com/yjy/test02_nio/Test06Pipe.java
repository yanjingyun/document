package com.yjy.test02_nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;

import org.junit.Test;

// 测试DatagramChannel：先开启一个receive，再开启两个send，查看是否阻塞
public class Test06Pipe {
	
	@Test
	public void send() throws IOException {
		// 1.获取管道
		Pipe pipe = Pipe.open();
		
		// 这个放到一个线程中，用于发送
		// 2.将缓冲区的数据写入管道
		SinkChannel sinkChannel = pipe.sink();
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.put("通过单向管道发送数据".getBytes());
		buf.flip();
		sinkChannel.write(buf);
		
		// 这个也放到一个线程中，用于接收
		// 3.读取缓冲区中的数据
		SourceChannel sourceChannel = pipe.source();
		buf.flip();
		int len = sourceChannel.read(buf);
		System.out.println(new String(buf.array(), 0, len));
		
		sourceChannel.close();
		sinkChannel.close();
	}
}
