package com.yjy.test03_reactor.v1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

/**
 * Reactor单线程模型
 */
public class Server implements Runnable {

    private final Selector selector;

    public static void main(String[] args) throws IOException {
        Server server = new Server(8080);
        server.run();
    }

    private Server(int port) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port), 1024);
        selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor(selector, selectionKey));
        System.out.println("========= 服务端启动成功 =========");
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                int count = selector.select();
                System.out.printf("线程%s本次监听到就绪事件个数:%s %n", Thread.currentThread().getName(), count);
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                // 遍历就绪事件，然后进行分发
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    Runnable runnable = (Runnable) key.attachment();
                    System.out.println(runnable); // 可能是Acceptor对象、或Dispatcher对象
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        System.out.println("客户端意外关闭");
                        e.printStackTrace();
                    } finally {
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}