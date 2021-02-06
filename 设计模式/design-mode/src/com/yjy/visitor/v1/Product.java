package com.yjy.visitor.v1;

import java.time.LocalDate;

/**
 * 步骤1：首先是各种商品的实体类，包括糖、酒、和水果，它们都应该共享一些共通属性，那就先抽象出一个商品类
 */
public abstract class Product {

    protected String name;// 品名
    protected LocalDate producedDate;// 生产日期
    protected float price;// 价格

    public Product(String name, LocalDate producedDate, float price) {
        this.name = name;
        this.producedDate = producedDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getProducedDate() {
        return producedDate;
    }

    public void setProducedDate(LocalDate producedDate) {
        this.producedDate = producedDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}