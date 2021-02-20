package com.yjy.broker;

public class ServiceA extends AbstractService {

    public ServiceA(RegisterCenter registerCenter) {
        super(registerCenter);
    }

    @Override
    public void doSomething() {
        System.out.println("I'm Service A");
    }

    public void callB(){
        this.doSomething();
        System.out.println("调用Service B");
        registerCenter.call(ServiceB.class);
    }
}