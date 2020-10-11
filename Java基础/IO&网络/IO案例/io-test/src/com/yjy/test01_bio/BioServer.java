package com.yjy.test01_bio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server端
 */
public class BioServer {
	static byte[] bytes = new byte[1024];

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(8080));

			while (true) {
				// 阻塞 等待连接
				Socket socket = serverSocket.accept();
				System.out.println("获取客户端连接");
				// 阻塞 read读了多少字节
				socket.getInputStream().read(bytes);
				String content = new String(bytes);
				System.out.println("从服务端读取内容：" + content);
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
