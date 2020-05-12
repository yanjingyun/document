package com.yjy.test_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class QQServerNio {

	static byte[] bytes = new byte[1024];
	static List<SocketChannel> list = new ArrayList<>();
	static ByteBuffer byteBuffer = ByteBuffer.allocate(512);
	public static void main(String[] args) throws InterruptedException {
		try {
			// listener
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(8080));

			while (true) {
				// ×èÈû
				SocketChannel socketChannel = serverSocketChannel.accept();
				if (socketChannel == null) {
					Thread.sleep(1000);
					System.out.println("no connect...");
					
					for (SocketChannel client : list) {
						int k = client.read(byteBuffer);
						if (k > 0) {
							byteBuffer.flip();
							System.out.println(byteBuffer.toString());
						}
					}
				} else {
					socketChannel.configureBlocking(false);
					list.add(socketChannel);
					for (SocketChannel client : list) {
						int k = client.read(byteBuffer);
						if (k > 0) {
							byteBuffer.flip();
							System.out.println(byteBuffer.toString());
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
