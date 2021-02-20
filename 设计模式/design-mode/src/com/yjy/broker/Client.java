package com.yjy.broker;

public class Client {
    public static void main(String[] args) {
        RegisterCenter registerCenter = new RegisterCenter();

        System.out.println(">>> 测试1：ServiceA注册到注册中心，并调用ServiceB服务（此时未启动）");
        ServiceA serviceA = new ServiceA(registerCenter);
        serviceA.callB();

        System.out.println(">>> 测试2：ServiceB注册到注册中心，并调用ServiceA服务");
        ServiceB serviceB = new ServiceB(registerCenter);
        serviceB.callA();

        System.out.println(">>> 测试3：ServiceA重新调用ServiceB服务");
        serviceA.callB();
    }
}