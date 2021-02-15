package com.yjy.strategy2.v1;

public class GoldVip implements CalPrice {
    @Override
    public String menberType() {
        return "金牌会员，7折";
    }

    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.7;
    }
}