package com.yjy.strategy2.v1;

public interface CalPrice {

    String menberType();
    //根据原价返回一个最终的价格
    Double calPrice(Double orgnicPrice);
}