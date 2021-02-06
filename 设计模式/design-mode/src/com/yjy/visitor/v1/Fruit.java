package com.yjy.visitor.v1;

import java.time.LocalDate;

/**
 * 步骤2：具体商品，如糖果、酒、和水果
 */
public class Fruit extends Product {// 水果
    private float weight;

    public Fruit(String name, LocalDate producedDate, float price, float weight) {
        super(name, producedDate, price);
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

}