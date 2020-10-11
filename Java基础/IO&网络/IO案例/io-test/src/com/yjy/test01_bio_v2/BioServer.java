package com.yjy.test01_bio_v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Server端（多线程处理read）
 */
public class BioServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Executor threadPool = Executors.newCachedThreadPool();
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(8080));

			while (true) {
				// 阻塞 等待连接
				Socket socket = serverSocket.accept();
				System.out.println("获取客户端连接");
				
				// 优化：多线程处理read请求
				threadPool.execute(() -> {
					byte[] bytes = new byte[1024];
					try {
						socket.getInputStream().read(bytes); // 阻塞 read读了多少字节
					} catch (IOException e) {
						e.printStackTrace();
					}
					String content = new String(bytes);
					System.out.println("从服务端读取内容：" + content);
				});
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
