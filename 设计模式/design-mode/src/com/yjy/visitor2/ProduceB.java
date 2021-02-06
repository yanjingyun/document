package com.yjy.visitor2;

public class ProduceB extends Producer implements Acceptor {

    private String origin;

    public ProduceB(String name, float price, String origin) {
        super(name, price);
        this.origin = origin;
    }

    @Override
    public void get() {
        System.out.println(String.format("产品名称：%s，价格：%s，生产地：%s", getName(), getPrice(), getOrigin()));
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
