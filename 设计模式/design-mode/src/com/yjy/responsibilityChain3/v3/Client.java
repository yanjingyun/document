package com.yjy.responsibilityChain3.v3;

public class Client {
    public static void main(String[] args) {
//        // 方式1：责任链模式
//        VerifyAccountHandler handler = new VerifyAccountHandler();
//        VerifyRoleHanlder verifyRoleHanlder = new VerifyRoleHanlder();
//        VerifyPermissionHanlder verifyPermissionHanlder = new VerifyPermissionHanlder();
//        handler.next(verifyRoleHanlder);
//        verifyRoleHanlder.next(verifyPermissionHanlder);

        // 方式2：建造者模式+责任链模式
        Handler handler = new Handler.Builder()
                .add(new VerifyAccountHandler())
                .add(new VerifyRoleHanlder())
                .add(new VerifyPermissionHanlder()).build();

        System.out.println(">>>>>>>>测试1：全部通过");
        LoginUser user1 = new LoginUser("TestAA", "123", "admin", "admin");
        handler.handle(user1);

        System.out.println(">>>>>>>>测试2：用户名为空");
        LoginUser user2 = new LoginUser("", "123", "admin", "admin");
        handler.handle(user2);

        System.out.println(">>>>>>>>测试3：密码不正确");
        LoginUser user3 = new LoginUser("TestAA", "12345", "admin", "admin");
        handler.handle(user3);

        System.out.println(">>>>>>>>测试4：角色不允许访问");
        LoginUser user4 = new LoginUser("TestAA", "123", "user", "user");
        handler.handle(user4);

        System.out.println(">>>>>>>>测试5：权限不允许访问");
        LoginUser user5 = new LoginUser("TestAA", "123", "admin", "user");
        handler.handle(user5);

    }
}
