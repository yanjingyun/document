package com.yjy.decoration.v1;

public class Client {
    public static void main(String[] args) {
        //用装饰器包裹女孩show出来
        new Decorator(new Girl()).show();
        //结果：粉饰(女孩的素颜)
    }
}