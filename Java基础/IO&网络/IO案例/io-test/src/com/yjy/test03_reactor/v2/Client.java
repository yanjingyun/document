package com.yjy.test03_reactor.v2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 客户端
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8081));
        while (!socketChannel.finishConnect()) {
            // do nothing, 也可以将连接事件注册到socketChannel上，让复用器来监听连接是否就绪
        }
        ByteBuffer wrap = ByteBuffer.wrap("服务器你好！".getBytes());
        socketChannel.write(wrap);
        socketChannel.register(selector, SelectionKey.OP_READ);

        try {
            while (!Thread.interrupted()) {
                selector.select();
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    SocketChannel client = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(50);
                    client.read(byteBuffer);
                    byteBuffer.flip();
                    System.out.println("客户端接收到服务端消息：" + new String(byteBuffer.array(), "UTF-8"));
                    iterator.remove();

                    System.out.print("请输入发送内容:");
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    if (!"".equals(s)) {
                        ByteBuffer buffer = ByteBuffer.wrap(s.getBytes());
                        socketChannel.write(buffer);
                    }
                }
            }
        } catch (Exception e) {
            socketChannel.close();
            System.out.println("服务器意外关闭，客户端关闭连接");
        }
    }
}