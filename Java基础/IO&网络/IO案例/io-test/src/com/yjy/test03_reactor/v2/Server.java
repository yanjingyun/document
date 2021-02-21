package com.yjy.test03_reactor.v2;

import java.io.IOException;

public class Server {


    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            TCPReactor reactor = new TCPReactor(8081);
            reactor.run();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}