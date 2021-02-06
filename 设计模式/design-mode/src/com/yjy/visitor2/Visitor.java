package com.yjy.visitor2;

/**
 * 步骤2：定义访问者，通过重载实现访问所有的产品
 */
public class Visitor {

    public void visit(ProduceA producerA) {
        producerA.get();
    }

    public void visit(ProduceB producerB) {
        producerB.get();
    }
}
