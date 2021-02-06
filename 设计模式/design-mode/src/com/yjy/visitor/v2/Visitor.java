package com.yjy.visitor.v2;

/**
 * 步骤4：通过重载来访问不同的商品，先定义访问者接口，目的是对日后访问者的扩展
 */
public interface Visitor {// 访问者接口

    public void visit(Candy candy);// 糖果重载方法

    public void visit(Wine wine);// 酒类重载方法

    public void visit(Fruit fruit);// 水果重载方法
}