package com.yjy.test03_reactor.v2;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {

    private final ServerSocketChannel ssc;
    private final Selector selector;

    public Acceptor(Selector selector, ServerSocketChannel ssc) {
        this.ssc = ssc;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = ssc.accept(); // 接收client连接请求
            System.out.println(sc.socket().getRemoteSocketAddress() + " 客户端正在连接...");
            if (sc != null) {
                sc.configureBlocking(false); // 设置为非阻塞
                SelectionKey sk = sc.register(selector, SelectionKey.OP_READ); // SocketChannel向selector注册一个OP_READ事件
                selector.wakeup(); // 使一个阻塞住的selector操作立即返回
                sk.attach(new TCPHandler(sk, sc)); // 给key附加一个TCPHandler对象
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}