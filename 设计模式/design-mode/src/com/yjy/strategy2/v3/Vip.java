package com.yjy.strategy2.v3;

@PriceRegion(min = 10000, max = 20000)
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