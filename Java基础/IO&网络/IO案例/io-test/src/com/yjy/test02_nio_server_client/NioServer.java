package com.yjy.test02_nio_server_client;

import java.io.IOException;
import java.net.InetSocketAddress;
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
		int port = 8080;
		NioServer server = new NioServer(port);
		server.listen();
	}

	public NioServer(int port) throws IOException {
		selector = Selector.open(); // 打开多路复用器selector

		ServerSocketChannel channel = ServerSocketChannel.open(); // 打开服务器套接字通道(channel)
		channel.configureBlocking(false); // 配置通道为非阻塞的状态
		channel.socket().bind(new InetSocketAddress(port)); // 获取通道关联的服务器socket，并绑定地址和端口
		channel.register(selector, SelectionKey.OP_ACCEPT); // 将通道channel在多路复用器selector上注册为接收操作，等待连接
		System.out.println("NioServer Start, port=" + port);
	}

	// 监听
	private void listen() throws IOException {
		while (true) {
			// 选择一组键，它们对应的通道已经准备好IO操作了
			// 阻塞，只有在选择了至少一个通道、调用此选择器的wakeup方法或中断当前线程（以最先出现的方式）之后，它才返回
			selector.select(1000); // 最大阻塞时间1s
			// 获取多路复用器的事件值SelectionKey，并存放在迭代器中
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey selectionKey = iterator.next();
				iterator.remove();
				handleKey(selectionKey); // 处理请求
			}
		}
	}

	// 处理请求
	private void handleKey(SelectionKey key) throws IOException {
		if (!key.isValid()) // 判断所传selectionKey值是否可用
			return;

		if (key.isAcceptable()) { // key值为OP_ACCEPT,，在判断是否为接收操作
			ServerSocketChannel ssc = (ServerSocketChannel) key.channel();// 获取key值所对应的channel
			SocketChannel sc = ssc.accept();
			sc.configureBlocking(false); // 设置为接收非阻塞通道
			sc.register(selector, SelectionKey.OP_READ);// 并把这个通道注册为OP_READ
		}

		if (key.isReadable()) { // key值是否为OP_READ,通过上面的注册后，经过轮询后就会是此操作
			SocketChannel sc = (SocketChannel) key.channel();// 获取key对应的channel
			ByteBuffer readbuf = ByteBuffer.allocate(1024);
			int count = sc.read(readbuf);// 从channel中读取byte数据并存放readbuf
			if (count > 0) {
				readbuf.flip(); // 检测时候为完整的内容,若不是则返回完整的
				byte[] bytes = new byte[readbuf.remaining()];
				readbuf.get(bytes);
				String readStr = new String(bytes, "UTF-8");// 把读取的数据转换成string
				System.out.println("服务器接受到命令：" + readStr);
				dowrite(sc, readStr);// 获取到当前时间后，就需要把当前时间的字符串发送出去
			} else if (count < 0) {
				key.cancel();
				sc.close();
			}
		}
	}

	/** 服务器的业务操作，将当前时间写到通道内 */
	private void dowrite(SocketChannel sc, String readStr) throws IOException {
		String sendStr = LocalDateTime.now().toString();
		System.out.println(String.format("服务器接收到内容：%s，并发送内容：%s", readStr, sendStr));
		byte[] bytes = sendStr.getBytes(); // 将当前时间序列化
		ByteBuffer writebuf = ByteBuffer.allocate(bytes.length);
		writebuf.put(bytes);// 将序列化的内容写入分配的内存
		writebuf.flip();
		sc.write(writebuf); // 将此内容写入通道
	}
}
