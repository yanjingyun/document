package com.yjy.responsibilityChain2.v1;

/**
 * 是否重复登录校验
 */
public class RepeatLoginHandler extends Handler {
    @Override
    public void doHandler(User user) {
        if (checkLoginOrNot(user)) {
            System.out.println("用户未登录，校验通过");
            doNextHandle(user);
        } else {
            System.out.println("用户已登录，校验不通过");
        }
    }

    // code为123时表示未重复登录
    private boolean checkLoginOrNot(User user) {
        return "123".equals(user.getCode());
    }
}
