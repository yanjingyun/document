package com.yjy.responsibilityChain.v2;

public class Client {
    public static void main(String[] args) {
        CEO ceo = new CEO("刘备");
        Manager manager = new Manager("关羽");
        Staff staff = new Staff("张飞");
        staff.setNextApprover(manager).setNextApprover(ceo); // 链式调用

        System.out.println("***************给员工审核1000元***************");
        staff.approve(1000);
        /***********************
         审批通过。【员工：张飞】
         ***********************/

        System.out.println("***************给员工审核4000元***************");
        staff.approve(4000);
        /***********************
         无权审批，升级处理。【员工：张飞】
         审批通过。【经理：关羽】
         ***********************/

        System.out.println("***************给员工审核9000元***************");
        staff.approve(9000);
        /***********************
         无权审批，升级处理。【员工：张飞】
         无权审批，升级处理。【经理：关羽】
         审批通过。【CEO：刘备】
         ***********************/

        System.out.println("***************给员工审核88000元***************");
        staff.approve(88000);
        /***********************
         无权审批，升级处理。【员工：张飞】
         无权审批，升级处理。【经理：关羽】
         驳回申请。【CEO：刘备】
         ***********************/

        System.out.println("***************给经理审核9000元***************");
        manager.approve(88000);
        /***********************
         无权审批，升级处理。【经理：关羽】
         驳回申请。【CEO：刘备】
         ***********************/
    }
}