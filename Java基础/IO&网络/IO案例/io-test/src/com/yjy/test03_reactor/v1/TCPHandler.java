package com.yjy.test03_reactor.v1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * 事件分发器，主要用来处理读写事件和业务逻辑，业务逻辑可进行抽离，看具体场景
 */
public class TCPHandler implements Runnable {

    private final SelectionKey selectionKey;
    public TCPHandler(SelectionKey selectionKey) {
        this.selectionKey = selectionKey;
    }

    @Override
    public void run() {
        SocketChannel sc = (SocketChannel) selectionKey.channel();
        try {
            if (selectionKey.isReadable()) {
                read(sc);
            } else if (selectionKey.isWritable()) {
                send(sc);
            }
        } catch (Exception e) {
            try {
                System.out.printf("客户端%s关闭 %n", sc.getRemoteAddress());
                sc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void read(SocketChannel socketChannel) throws Exception {
        // 读取客户端的消息
        ByteBuffer buffer = ByteBuffer.allocate(50);
        socketChannel.read(buffer);
        buffer.flip();
        String msg = new String(buffer.array(), "UTF-8");
        System.out.println("接收到客户端的消息：" + msg);
        // 模拟业务逻辑处理，所花费时间
        process();

        // 注册写事件，写事件一般不注册，因为可能出现服务器一支对写事件就绪
        selectionKey.interestOps(SelectionKey.OP_WRITE);
    }

    private void send(SocketChannel socketChannel) throws IOException {
        ByteBuffer wrap = ByteBuffer.wrap(("服务器接收消息时间"+ LocalTime.now()).getBytes());
        socketChannel.write(wrap);
        selectionKey.interestOps(SelectionKey.OP_READ);
    }

    /**
     * 业务逻辑处理
     */
    private void process() throws InterruptedException {
        // handle business logic,单线程的反应器模式的弊端主要是如果业务逻辑处理事件过长会造成长时间不无法处理select()方法
        // 进而导致客户端阻塞，所以一般将业务逻辑交由线程池来进行异步处理，防止反应器被阻塞
        TimeUnit.SECONDS.sleep(5);
    }
}