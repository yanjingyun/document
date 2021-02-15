package com.yjy.strategy2.v1;

public class Orgnic implements CalPrice {

    @Override
    public String menberType() {
        return "普通玩家，无折扣";
    }

    @Override
    public Double calPrice(Double orgnicPrice) {
        return orgnicPrice;
    }
}