package com.yjy.strategy2.v1;

public class SuperVip implements CalPrice {
    @Override
    public String menberType() {
        return "超级会员，8折";
    }

    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice * 0.8;
    }
}