package com.yjy.visitor.v1;

import java.time.LocalDate;

/**
 * 步骤5：测试
 */
public class Client {
    public static void main(String[] args) {
        // 测试1：单个商品访问
        // 小黑兔奶糖，生产日期：2018-10-1，原价：￥20.00
        Candy candy = new Candy("小黑兔奶糖", LocalDate.of(2018, 10, 1), 20.00f);
        Visitor visitor = new VisitorImpl(LocalDate.of(2019, 1, 1));
        visitor.visit(candy);
        /****************************
         结算日期：2019-01-01
         =====糖果【小黑兔奶糖】打折后价格=====
         ￥18.00
         ****************************/

//        // 测试2：多个商品访问(多种商品结算，discountVisitor.visit(product); 不可用，引出v2测试)
//        // 三件商品加入购物车
//        List<Product> products = Arrays.asList(
//                new Candy("小黑兔奶糖", LocalDate.of(2018, 10, 1), 20.00f),
//                new Wine("猫泰白酒", LocalDate.of(2017, 1, 1), 1000.00f),
//                new Fruit("草莓", LocalDate.of(2018, 12, 26), 10.00f, 2.5f)
//        );
//
//        Visitor discountVisitor = new VisitorImpl(LocalDate.of(2018, 1, 1));
//        // 迭代购物车轮流结算
//        for (Product product : products) {
//            discountVisitor.visit(product);// 此处报错
//        }
    }
}