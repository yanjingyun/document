package com.yjy.responsibilityChain2.v2;

public class Client {
    public static void main(String[] args) {
        // 校验顺序 验证码->用户名密码-》是否重复登录
        Handler handler = new Handler.Builder()
                .add(new CodeHandler())
                .add(new AuthHandler())
                .add(new RepeatLoginHandler())
                .build();

        System.out.println(">>>>>测试1：全部正确");
        User user1 = new User("admin", "admin", "123");
        handler.doHandler(user1);

        System.out.println(">>>>>测试2：验证码不通过");
        User user2 = new User("admin", "admin", "333");
        handler.doHandler(user2);

        System.out.println(">>>>>测试3：用户名或密码不通过");
        User user3 = new User("admin", "admin22", "123");
        handler.doHandler(user3);

        System.out.println(">>>>>测试4：用户重复登录不通过");
        User user4 = new User("admin", "admin", "1234");
        handler.doHandler(user4);

    }
}
