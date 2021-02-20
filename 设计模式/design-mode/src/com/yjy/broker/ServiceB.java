package com.yjy.broker;

public class ServiceB extends AbstractService {

    public ServiceB(RegisterCenter registerCenter) {
        super(registerCenter);
    }

    @Override
    public void doSomething() {
        System.out.println("I'm Service B");
    }

    public void callA(){
        this.doSomething();
        System.out.println("调用Service A");
        registerCenter.call(ServiceA.class);
    }
}