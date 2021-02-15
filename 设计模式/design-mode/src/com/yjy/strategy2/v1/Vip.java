package com.yjy.strategy2.v1;

public class Vip implements CalPrice {
    @Override
    public String menberType() {
        return "普通会员，9折";
    }

    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.9;
    }
}