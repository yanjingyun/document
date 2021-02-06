package com.yjy.visitor2;

public class ProduceA extends Producer implements Acceptor {
    public ProduceA(String name, float price) {
        super(name, price);
    }

    @Override
    public void get() {
        System.out.println(String.format("产品名称：%s，价格：%s", getName(), getPrice()));
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
