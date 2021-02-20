package com.yjy.broker;

public abstract class AbstractService {
    protected RegisterCenter registerCenter;

    public AbstractService(RegisterCenter registerCenter) {
        registerCenter.registered(this);
        this.registerCenter = registerCenter;
    }

    public abstract void doSomething();
}