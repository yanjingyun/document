package com.yjy.visitor2;

/**
 * 步骤1：先定义抽象产品，并定义两个具体产品ProducerA和ProducerB
 */
public abstract class Producer {
    private String name;
    private float price;

    public Producer(String name, float price) {
        this.name = name;
        this.price = price;
    }

    abstract void get();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
