package com.yjy.responsibilityChain.v2;

public class Staff extends Approver {

    public Staff(String name) {
        super(name);
    }

    @Override
    public void approve(int amount) {
        if (amount <= 1000) {
            System.out.println("审批通过。【员工：" + name + "】");
        } else {
            System.out.println("无权审批，升级处理。【员工：" + name + "】");
            this.nextApprover.approve(amount);
        }
    }
}