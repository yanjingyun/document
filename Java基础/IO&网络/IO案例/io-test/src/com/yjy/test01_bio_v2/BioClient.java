package com.yjy.test01_bio_v2;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * bio客户端
 */
public class BioClient {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 8080);
			System.out.println("请输入内容：");
			Scanner in = new Scanner(System.in);
			String txt = in.next();
			socket.getOutputStream().write(txt.getBytes());

			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}