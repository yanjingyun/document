package com.yjy.test02_nio_server_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO服务端
 * NIO是非阻塞IO，其核心组件就是多路复用器Selector和Channel，所有Channel都要在Selector上去注册，来实现非阻塞的过程。
 * Selector会不断轮询注册在其上Channel，如果某个Channel上面发生读写事件，这个Channel就处于就绪状态，会被Selector轮询出来，然后通过SelectKey可以获取就绪Channel的集合，进行后续的IO操作。
 * 一个Selector可以同时轮询多个Channel，因为JDK使用了epoll()代替传统的select实现，所以没有最大连接句柄1024/2048的限制。所以，只需要一个线程负责Selector的轮询，就可以接入成千上万的客户端。
 */
public class NioServer {

	private Selector selector; // 多路复用器

	public static void main(String[] args) throws IOException {
		NioServer server = new NioServer(8080);
		server.listen();
	}

	public NioServer(int port) throws IOException {
		// 初始化多路复用器Selector
		selector = Selector.open();
		// 初始化服务器套接字通道ServerSocketChannel
		ServerSocketChannel channel = ServerSocketChannel.open();
		// 通道设置为非阻塞模式
		channel.configureBlocking(false);
		// 获取该通道的服务器套接字ServerSocket，并绑定ip、port，设置backlog
		// backlog指队列的容量，提供了容量限制的功能，避免太多客户端占用太多服务器资源。ServerSocketChannel有一个队列，存放没有来得及处理的客户端,服务器每次accept，就会从队列中去一个元素。
		channel.socket().bind(new InetSocketAddress(port), 1024);
		// 1、将该通道注册到Selector上，并监听accept事件（就是客户端的connect请求），等待连接
		channel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("server初始化完成！");
	}

	// 监听
	private void listen() throws IOException {
		while (true) {
			// 多路复用器开始工作（轮询），选择已就绪的通道，等待某通道准备就绪时最多阻塞1s，若超时则返回
			selector.select(1000);
			// 获取所有已就绪的事件SelectionKey
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			SelectionKey selectionKey;
			while (iterator.hasNext()) {
				selectionKey = iterator.next();
				iterator.remove(); // 移除已处理
				try {
					// 处理请求
					handleKey(selectionKey);
				} catch (Exception e) {
					e.printStackTrace();
					// 将抛出异常的客户端从服务器中剔除！！！
					if (selectionKey != null) {
						selectionKey.cancel();
						if (selectionKey.channel() != null) {
							selectionKey.channel().close();
						}
					}
				}
			}
		}
	}

	// 处理请求
	private void handleKey(SelectionKey key) throws IOException {
		// 判断所传selectionKey值是否可用
		if (!key.isValid()) return;

		if (key.isAcceptable()) {
			// 获取key值所对应的通道（服务器通道）
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
			// 执行阻塞方法获取客户端通道（等待客户端资源）
			SocketChannel sc = ssc.accept();
			// 通道设置为非阻塞模式
			sc.configureBlocking(false); // 设置为接收非阻塞通道

			// 将该通道注册到多路复用器Selector上，并设置为可读状态
			sc.register(selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) { // 读取数据
			// 获取key值所对应的通道（客户端通道）
			SocketChannel sc = (SocketChannel) key.channel();
			ByteBuffer readBuffer = ByteBuffer.allocate(1024);
			int count = sc.read(readBuffer);// 从channel中读取byte数据并存放readbuf
			if (count > 0) {
				// 反转缓冲区（复位）
				readBuffer.flip();
				byte[] bytes = new byte[readBuffer.remaining()];
				// 接收缓冲区数据
				readBuffer.get(bytes);
				String body = new String(bytes).trim();
				System.out.println("服务器接收消息：" + body);

				// 给客户端回写消息
				doWrite(sc, body);
			} else if (count < 0) {
				// 对端链路关闭
				key.cancel();
				sc.close();
			}
		}
	}
	
	private void doWrite(SocketChannel sc, String body) throws IOException {
		ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
		String sendStr = String.format("服务器接收【%s】时间为%s", body, LocalDateTime.now().toString());
		System.out.printf("服务器接收到内容：%s，并发送内容：%s %n", body, sendStr);
		byte[] bytes = sendStr.getBytes();
		writeBuffer.put(bytes);// 将序列化的内容写入分配的内存
		writeBuffer.flip();
		sc.write(writeBuffer); // 将此内容写入通道
	}
}
