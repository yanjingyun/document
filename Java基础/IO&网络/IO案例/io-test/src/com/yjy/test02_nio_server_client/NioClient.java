package com.yjy.test02_nio_server_client;

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

	public static void main(String[] args) throws Exception {
		// 打开多路复用器
		Selector selector = Selector.open();
		// 打开通道socketchannel
		SocketChannel channel = SocketChannel.open();
		// 该通道设置为非阻塞方式
		channel.configureBlocking(false);
		// 通过ip、port连接到服务器
		channel.connect(new InetSocketAddress("localhost", 8080));
		// 向多路复用器注册，并设置为可读事件
		channel.register(selector, SelectionKey.OP_CONNECT);
		System.out.println("client初始化完成！");

		while (true) {
			// 模拟逻辑处理时间
			Thread.sleep(1000);

			// 选择一组键，其相应的通道已为 I/O 操作准备就绪。 此方法执行处于阻塞模式的选择操作。
			// 阻塞等待1s，若超时则返回
			selector.select(1000);
			// 获取多路复用器的事件值SelectionKey，并存放在迭代器中
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			for (SelectionKey selectionKey : selectionKeys) {
				if (selectionKey.isConnectable()) { // 一开始的时候，客户端需要连接服务器操作，所以检测是否为连接状态
					System.out.println("客户端连接");
					SocketChannel client = (SocketChannel) selectionKey.channel();
					if (client.finishConnect()) {
						System.out.println("客户端连接成功！");
						ByteBuffer sendbuffer = ByteBuffer.allocate(1024);
						sendbuffer.put("服务端你好".getBytes());
						sendbuffer.flip();
						client.write(sendbuffer);
						client.register(selector, SelectionKey.OP_READ);
					}
				} else if (selectionKey.isReadable()) { // 读操作
					SocketChannel client = (SocketChannel) selectionKey.channel();
					ByteBuffer receivebuffer = ByteBuffer.allocate(1024);
					int count = client.read(receivebuffer); // 读取服务器发送来的数据到缓冲区中
					if (count > 0) {
						String receiveText = new String(receivebuffer.array(), 0, count);
						System.out.println("客户端接收到消息：" + receiveText);
						client.register(selector, SelectionKey.OP_WRITE);
					}

				} else if (selectionKey.isWritable()) { // 写操作
					String req = "sc消息" + (++flag);
					System.out.println("客户端发送消息：" + req);
					ByteBuffer writebuf = ByteBuffer.allocate(1024);
					writebuf.put(req.getBytes());
					writebuf.flip();
					SocketChannel client = (SocketChannel) selectionKey.channel();
					client.write(writebuf);
					client.register(selector, SelectionKey.OP_READ);
				}
			}
			selectionKeys.clear();
		}
	}
}
