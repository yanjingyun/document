package com.yjy.responsibilityChain.v1;

public class CEO {

    private String name;

    public CEO(String name) {
        this.name = name;
    }

    public boolean approve(int amount) {
        if (amount <= 10000) {
            System.out.println("审批通过。【CEO：" + name + "】");
            return true;
        } else {
            System.out.println("驳回申请。【CEO：" + name + "】");
            return false;
        }
    }

}