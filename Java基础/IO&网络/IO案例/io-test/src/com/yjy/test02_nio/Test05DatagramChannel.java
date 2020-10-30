package com.yjy.test02_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Scanner;

import org.junit.Test;

// 测试DatagramChannel：先开启一个receive，再开启两个send，查看是否阻塞
public class Test05DatagramChannel {
	
	@Test
	public void send() throws IOException {
		DatagramChannel dc = DatagramChannel.open();
		dc.configureBlocking(false);
		
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String str = scan.next();
			buf.put((LocalDateTime.now() + " >> " + str).getBytes());
			buf.flip();
			dc.send(buf, new InetSocketAddress("127.0.0.1", 8080));
			buf.clear();
		}
		
		dc.close();
		scan.close();
	}
	
	@Test
	public void receive() throws IOException {
		DatagramChannel dc = DatagramChannel.open();
		dc.configureBlocking(false);
		
		dc.bind(new InetSocketAddress(8080));
		
		Selector selector = Selector.open();
		dc.register(selector, SelectionKey.OP_READ);
		
		while (selector.select() > 0) {
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			
			while (iterator.hasNext()) {
				SelectionKey sk = iterator.next();
				
				if (sk.isReadable()) {
					ByteBuffer buf = ByteBuffer.allocate(1024);
					dc.receive(buf);
					buf.flip();
					System.out.println("接收到数据：" + new String(buf.array(), 0, buf.limit()));
					buf.clear();
				}
			}
			iterator.remove();
		}
	}
}
