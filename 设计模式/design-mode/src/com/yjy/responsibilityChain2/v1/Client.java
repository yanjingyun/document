package com.yjy.responsibilityChain2.v1;

public class Client {
    public static void main(String[] args) {
        Handler codeHandler = new CodeHandler();
        Handler authHandler = new AuthHandler();
        Handler repeatLoginHandler = new RepeatLoginHandler();

        // 校验顺序 验证码->用户名密码-》是否重复登录
        codeHandler.next(authHandler);
        authHandler.next(repeatLoginHandler);

        System.out.println(">>>>>测试1：全部正确");
        User user1 = new User("admin", "admin", "123");
        codeHandler.doHandler(user1);

        System.out.println(">>>>>测试2：验证码不通过");
        User user2 = new User("admin", "admin", "333");
        codeHandler.doHandler(user2);

        System.out.println(">>>>>测试3：用户名或密码不通过");
        User user3 = new User("admin", "admin22", "123");
        codeHandler.doHandler(user3);

        System.out.println(">>>>>测试4：用户重复登录不通过");
        User user4 = new User("admin", "admin", "1234");
        codeHandler.doHandler(user4);

    }
}
