package com.yjy.strategy2.v3;

import java.util.ArrayList;
import java.util.List;

public class CalPriceFactory {

    // 后续使用spring，能直接注入某接口的所有实现类
    private static List<CalPrice> calPriceList = new ArrayList<CalPrice>() {{
        add(new Orgnic());
        add(new Vip());
        add(new SuperVip());
        add(new GoldVip());
    }};

    private CalPriceFactory() {
    }

    //根据玩家的总金额产生相应的策略
    public static CalPrice createCalPrice(Player player) {
        for (CalPrice calPrice : calPriceList) {
            PriceRegion validRegion = calPrice.getClass().getAnnotation(PriceRegion.class);
            if (validRegion != null) {
                if (player.getTotalAmount() >= validRegion.min() && player.getTotalAmount() < validRegion.max()) {
                    return calPrice;
                }
            }
        }
        throw new RuntimeException("策略获得失败");
    }
}
