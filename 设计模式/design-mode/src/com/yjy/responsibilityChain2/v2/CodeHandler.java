package com.yjy.responsibilityChain2.v2;

/**
 * 验证码校验
 */
public class CodeHandler extends Handler {
    @Override
    public void doHandler(User user) {
        if (checkCode(user.getCode())) {
            System.out.println("验证码正确，校验通过");
            doNextHandle(user);
        } else {
            System.out.println("验证码不正确，校验不通过");
        }
    }

    private boolean checkCode(String code) {
        return code.startsWith("123");
    }
}
