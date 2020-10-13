package com.yjy.test02_nio_server_client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * nio服务端
 */
public class NioServer {

	private int flag = 0;
	// 缓冲区大小/
	private int BLOCK = 4096;
	// 接受数据缓冲区/
	private ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);
	// 发送数据缓冲区/
	private ByteBuffer receivebuffer = ByteBuffer.allocate(BLOCK);
	private Selector selector;

	public static void main(String[] args) throws IOException {
		int port = 7788;
		NioServer server = new NioServer(port);
		server.listen();
	}

	public NioServer(int port) throws IOException {
		// 打开服务器套接字通道
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		// 服务器配置为非阻塞
		serverSocketChannel.configureBlocking(false);
		// 获取与此通道关联的服务器socket，并进行服务绑定
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(port));

		// 获取选择器Selector，并将服务器socket注册到selector中，等待连接
		selector = Selector.open();
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		System.out.println("Server Start----7788:");
	}

	// 监听
	private void listen() throws IOException {
		while (true) {
			// 选择一组键，它们对应的通道已经准备好IO操作了
			// 阻塞，只有在选择了至少一个通道、调用此选择器的wakeup方法或中断当前线程（以最先出现的方式）之后，它才返回
			selector.select();
			// 返回此选择器的选择键集。
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
	private void handleKey(SelectionKey selectionKey) throws IOException {
		// 接受请求
		ServerSocketChannel server = null;
		SocketChannel client = null;
		String receiveText;
		int count = 0;
		// 测试此键的通道是否已准备好接受新的套接字连接。
		if (selectionKey.isAcceptable()) {
			// 返回为其创建此键的通道
			server = (ServerSocketChannel) selectionKey.channel();
			// 接受与此通道的套接字建立的连接，返回的套接字通道（如果有）将处于阻塞模式，可调整此通道的阻塞模式
			client = server.accept();
			client.configureBlocking(false); //非阻塞模式
			// 注册到selector，等待连接
			client.register(selector, SelectionKey.OP_READ);
		} else if (selectionKey.isReadable()) {
			// 返回为之创建此键的通道
			client = (SocketChannel) selectionKey.channel();
			// 将缓冲区清空以备下次读取
			receivebuffer.clear();
			// 读取服务器发送来的数据到缓冲区中
			count = client.read(receivebuffer);
			if (count > 0) {
				receiveText = new String(receivebuffer.array(), 0, count);
				System.out.println("服务器端->客户端：" + receiveText);
				client.register(selector, SelectionKey.OP_WRITE);
			}
		} else if (selectionKey.isWritable()) {
			// 将缓冲区清空以备下次写入
			sendbuffer.clear();
			// 返回为之创建此键的通道。
			client = (SocketChannel) selectionKey.channel();
			String sendText = "服务端消息==" + flag++;
			// 向缓冲区中输入数据
			sendbuffer.put(sendText.getBytes());
			// 将缓冲区各标志复位,因为向里面put了数据标志被改变要想从中读取数据发向服务器,就要复位
			sendbuffer.flip();
			// 输出到通道
			client.write(sendbuffer);
			System.out.println("服务器端->客户端：" + sendText);
			client.register(selector, SelectionKey.OP_READ);
		}
	}
}
