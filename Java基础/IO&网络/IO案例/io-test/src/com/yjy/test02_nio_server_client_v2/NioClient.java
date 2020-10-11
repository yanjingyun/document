package com.yjy.test02_nio_server_client_v2;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * nio客户端
 */
public class NioClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 8080);
			System.out.println("请输入内容：");
			Scanner in = new Scanner(System.in);
			String txt = in.next();
			socket.getOutputStream().write(txt.getBytes());
			
			byte[] b = new byte[1024];
			socket.getInputStream().read(b);
			System.out.println(new String(b));
			
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
