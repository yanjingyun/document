package com.yjy.test_nio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		
		try {
			Socket socket = new Socket("127.0.0.1", 8080);
			Scanner in = new Scanner(System.in);
			String txt = in.next();
			socket.getOutputStream().write(txt.getBytes());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
