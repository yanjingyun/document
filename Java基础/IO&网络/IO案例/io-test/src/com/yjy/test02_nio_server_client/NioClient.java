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

	// 标识数字/
	private static int flag = 0;
	// 缓冲区大小/
	private static int BLOCK = 4096;
	// 接受数据缓冲区/
	private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
	// 发送数据缓冲区/
	private static ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);

	public static void main(String[] args) throws IOException {
		// 打开socket通道
		SocketChannel socketChannel = SocketChannel.open();
		// 设置为非阻塞方式
		socketChannel.configureBlocking(false);
		// 打开选择器
		Selector selector = Selector.open();
		// 注册连接服务端socket动作
		socketChannel.register(selector, SelectionKey.OP_CONNECT);
		// 连接服务器端地址
		socketChannel.connect(new InetSocketAddress("localhost", 7788));

		// 分配缓冲区大小内存
		Set<SelectionKey> selectionKeys;
		SocketChannel client;
		String receiveText;
		String sendText;
		int count = 0;

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 选择一组键，其相应的通道已为 I/O 操作准备就绪。
			// 此方法执行处于阻塞模式的选择操作。
			selector.select();
			// 选择器的已选择键集
			selectionKeys = selector.selectedKeys();
			for (SelectionKey selectionKey : selectionKeys) {
				if (selectionKey.isConnectable()) {
					System.out.println("客户端连接");
					client = (SocketChannel) selectionKey.channel();
					// 判断此通道上是否正在进行连接操作。
					// 完成套接字通道的连接过程。
					if (client.isConnectionPending()) {
						client.finishConnect();
						System.out.println("客户端连接成功！");
						sendbuffer.clear();
						sendbuffer.put("服务端你好".getBytes());
						sendbuffer.flip();
						client.write(sendbuffer);
					}
					client.register(selector, SelectionKey.OP_READ);
				} else if (selectionKey.isReadable()) {
					client = (SocketChannel) selectionKey.channel();
					// 将缓冲区清空以备下次读取
					receivebuffer.clear();
					// 读取服务器发送来的数据到缓冲区中
					count = client.read(receivebuffer);
					if (count > 0) {
						receiveText = new String(receivebuffer.array(), 0, count);
						System.out.println("客户端->服务器端：" + receiveText);
						client.register(selector, SelectionKey.OP_WRITE);
					}

				} else if (selectionKey.isWritable()) {
					sendbuffer.clear();
					client = (SocketChannel) selectionKey.channel();
					sendText = "客户端消息==" + (flag++);
					sendbuffer.put(sendText.getBytes());
					// 将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
					sendbuffer.flip();
					client.write(sendbuffer);
					System.out.println("客户端->服务器：" + sendText);
					client.register(selector, SelectionKey.OP_READ);
				}
			}
			selectionKeys.clear();
		}
	}
}
