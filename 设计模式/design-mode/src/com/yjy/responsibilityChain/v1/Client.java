package com.yjy.responsibilityChain.v1;

public class Client {
    public static void main(String[] args) {
        int amount = 10000;//出差花费10000元
        // 先找员工张飞审批
        Staff staff = new Staff("张飞");
        if (!staff.approve(amount)) {
            //被拒，找关二爷问问。
            Manager manager = new Manager("关羽");
            if (!manager.approve(amount)) {
                //还是被拒，只能找老大了。
                CEO ceo = new CEO("刘备");
                ceo.approve(amount);
            }
        }
        /***********************
         无权审批，请找上级。【员工：张飞】
         无权审批，请找上级。【经理：关羽】
         审批通过。【CEO：刘备】
         ***********************/
    }
}