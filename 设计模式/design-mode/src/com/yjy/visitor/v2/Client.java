package com.yjy.visitor.v2;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 步骤5：测试，修复v1版本购物车不能处理多个商品
 * 具体实现：新增接待者接口，让所有商品实现该接口，并传入来访者，通过调用接待者方法来计算金额
 */
public class Client {
    public static void main(String[] args) {
        // 三件商品加入购物车，商品统统被泛化当作“接待者”
        List<Acceptable> products = Arrays.asList(
                new Candy("小黑兔奶糖", LocalDate.of(2018, 10, 1), 20.00f),
                new Candy("222奶糖", LocalDate.of(2017, 10, 1), 20.00f),
                new Wine("猫泰白酒", LocalDate.of(2017, 1, 1), 1000.00f),
                new Fruit("草莓", LocalDate.of(2018, 12, 26), 10.00f, 2.5f)
        );

        // 迭代购物车轮流结算
        Visitor visitor = new VisitorImpl(LocalDate.of(2019, 1, 1));
        for (Acceptable product : products) {
            // 由于泛型化后的商品像是被打了包裹一样让拜访者无法识别品类，所以在迭代里面我们让这些商品对象主动去“接待”来访者
            product.accept(visitor);
        }
        /***********************************
         结算日期：2019-01-01
         =====糖果【小黑兔奶糖】打折后价格=====
         ￥18.00
         =====糖果【222奶糖】打折后价格=====
         超过半年过期糖果，请勿食用！
         ￥0.00
         =====酒品【猫泰白酒】无折扣价格=====
         ￥1,000.00
         =====水果【草莓】打折后价格=====
         ￥12.50
         ***********************************/
    }
}