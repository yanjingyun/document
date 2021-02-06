package com.yjy.visitor.v2;

import java.time.LocalDate;

/**
 * 步骤2：具体商品，如糖果、酒、和水果
 */
public class Wine extends Product implements Acceptable {// 酒类

    public Wine(String name, LocalDate producedDate, float price) {
        super(name, producedDate, price);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);// 把自己交给拜访者。
    }
}