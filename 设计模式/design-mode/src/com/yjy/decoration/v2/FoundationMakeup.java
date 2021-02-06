package com.yjy.decoration.v2;

public class FoundationMakeup extends Decorator {

    public FoundationMakeup(Showable showable) {
        super(showable);//调用化妆品父类注入
    }

    @Override
    public void show() {
        System.out.print("打粉底(");
        super.show();
        System.out.print(")");
    }
}