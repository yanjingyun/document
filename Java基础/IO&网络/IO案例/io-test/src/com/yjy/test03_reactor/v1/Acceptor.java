package com.yjy.test03_reactor.v1;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * 连接处理器
 */
public class Acceptor implements Runnable {

    private final Selector selector;

    private final SelectionKey selectionKey;

    public Acceptor(Selector selector, SelectionKey selectionKey) {
        this.selector = selector;
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
        try {
            SocketChannel sc = ssc.accept();
            System.out.printf("客户端%s正在连接%n", sc.getRemoteAddress());
            sc.configureBlocking(false);
            // 向多路复用器注册读事件
            SelectionKey key = sc.register(selector, SelectionKey.OP_READ);
            // 给定key一个附加的TCPHandler对象
            key.attach(new TCPHandler(key));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}