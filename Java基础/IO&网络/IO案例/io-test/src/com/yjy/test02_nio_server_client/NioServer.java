package com.yjy.test02_nio_server_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * nio服务端
 */
public class NioServer {
	static byte[] bytes = new byte[1024];
	static List<SocketChannel> list = new ArrayList<>();
	static ByteBuffer byteBuffer = ByteBuffer.allocate(512);

	public static void main(String[] args) throws InterruptedException {
		try {
			// listener
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.bind(new InetSocketAddress(8080));

			while (true) {
				// 阻塞
				SocketChannel socketChannel = serverSocketChannel.accept();
				if (socketChannel == null) {
					Thread.sleep(1000);
					System.out.println("no connect...");
				} else {
					socketChannel.configureBlocking(false);
					list.add(socketChannel);
				}
				readContent();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 遍历有事件的socket
	private static void readContent() throws IOException {
		for (SocketChannel channel : list) {
			int k = channel.read(byteBuffer);
			if (k > 0) {
				byteBuffer.flip();
				System.out.println(byteBuffer.toString());
			}
		}
	}
}
