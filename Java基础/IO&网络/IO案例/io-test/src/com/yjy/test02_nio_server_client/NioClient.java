package com.yjy.test02_nio_server_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * nio客户端
 */
public class NioClient {

	private Selector selector; // 多路复用器

	private static int counter = 0;

	public static void main(String[] args) throws Exception {
		NioClient nioClient = new NioClient(8080);
		nioClient.listen();
	}

	public NioClient(int port) throws IOException {
		// 打开多路复用器
		selector = Selector.open();
		// 打开通道socketchannel
		SocketChannel channel = SocketChannel.open();
		// 该通道设置为非阻塞方式
		channel.configureBlocking(false);
		// 通过ip、port连接到服务器
		channel.connect(new InetSocketAddress("localhost", port));
		// 向多路复用器注册，并设置为可读事件
		channel.register(selector, SelectionKey.OP_CONNECT);
		System.out.println("client初始化完成！");
	}

	public void listen() throws Exception {
		while (true) {
			// 模拟逻辑处理时间，避免频繁请求服务器
			Thread.sleep(1000);

			// 选择一组键，其相应的通道已为 I/O 操作准备就绪。 此方法执行处于阻塞模式的选择操作。
			// 阻塞等待1s，若超时则返回
			selector.select(1000);

			// 获取并遍历所有selectionKey
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			for (SelectionKey selectionKey : selectionKeys) {
				// 处理该SelectionKey
				handleKey(selectionKey);
			}
			selectionKeys.clear();
		}
	}

	private void handleKey(SelectionKey selectionKey) throws IOException {
		if (!selectionKey.isValid()) return;

		SocketChannel sc = (SocketChannel) selectionKey.channel();
		if (selectionKey.isConnectable()) { // 连接操作
			System.out.println("客户端连接");
			if (sc.finishConnect()) { // 若已连接成功
				System.out.println("客户端连接成功");

				// 向管道写数据
				ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
				writeBuffer.put("服务端你好".getBytes());
				writeBuffer.flip();
				sc.write(writeBuffer);

				// 向多路复用器注册可读事件
				sc.register(selector, SelectionKey.OP_READ);
			}
		} else if (selectionKey.isReadable()) { // 读操作
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			int count = sc.read(readBuffer); // 读取服务器发送来的数据到缓冲区中
			if (count > 0) {
				String body = new String(readBuffer.array(), 0, count);
				System.out.println("客户端接收到消息：" + body);

				// 向多路复用器注册可写事件
				sc.register(selector, SelectionKey.OP_WRITE);
			}
		} else if (selectionKey.isWritable()) { // 写操作
			// 向管道写数据
			ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
			String requestBody = "sc消息" + counter++;
			System.out.println("客户端发送消息：" + requestBody);
			writeBuffer.put(requestBody.getBytes());
			writeBuffer.flip();
			sc.write(writeBuffer);

			// 向多路复用器注册可读事件
			sc.register(selector, SelectionKey.OP_READ);
		}
	}
}
