package com.yjy.visitor2;

import java.util.ArrayList;
import java.util.List;

/**
 * 步骤3：测试
 */
public class Main {
    public static void main(String[] args) {
//        // 测试1：单个产品的访问
//        Visitor visitor = new Visitor();
//        visitor.visit(new ProduceA("产品A", 100.00f));
//        visitor.visit(new ProduceB("产品B", 200.50f, "广东省广州市"));

//        // 测试2：多个产品的访问（常规写法，会报错）
//        List<Producer> list = new ArrayList<Producer>() {{
//            add(new ProduceA("产品A", 100.00f));
//            add(new ProduceB("产品B", 200.50f, "广东省广州市"));
//        }};
//        Visitor visitor = new Visitor();
//        for (Producer producer : list) {
//            visitor.visit(producer); // 会报错，只能是具体的产品
//        }

        // 测试3：解决测试2的问题（把每个产品变成接待者，即实现Acceptor接口，主动接待所有的访问者Visitor）
        List<Acceptor> list = new ArrayList<Acceptor>() {{
            add(new ProduceA("产品A", 100.00f));
            add(new ProduceB("产品B", 200.50f, "广东省广州市"));
        }};
        Visitor visitor = new Visitor();
        list.forEach(item -> {
            item.accept(visitor);
        });
    }
}
