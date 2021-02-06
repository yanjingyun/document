package com.yjy.decoration.v2;

public class Client {
    public static void main(String[] args) {
//        // 测试1：涂口红(打粉底(女孩的脸庞))
//        Showable madeupGirl1 = new Lipstick(new FoundationMakeup(new Girl()));
//        madeupGirl1.show();

        // 测试2：打粉底(涂口红(女孩的素颜))
        Showable madeupGirl2 = new FoundationMakeup(new Lipstick(new Girl()));
        madeupGirl2.show();
    }
}