package com.yjy.responsibilityChain3.v1;

public class VerifyAccountHandler extends Handler {
    @Override
    public void doHandler(LoginUser loginUser) {
        if (loginUser.getLoginName() == null || "".equals(loginUser.getLoginName())) {
            System.out.println("用户名不能为空");
            return;
        }
        if (loginUser.getPassword() == null || "".equals(loginUser.getPassword())) {
            System.out.println("密码不能为空");
            return;
        }
        if (!"123".equals(loginUser.getPassword())) {
            System.out.println("用户名或密码不正确");
            return;
        }

        System.out.println("用户名密码正确，校验通过");
        doNextHandler(loginUser);
    }
}
