package com.yjy.responsibilityChain3.v3;

public class VerifyAccountHandler extends Handler {
    @Override
    public void doHandler(LoginUser loginUser) {
        if (loginUser.getLoginName() == null || "".equals(loginUser.getLoginName())) {
            throw new RuntimeException("用户名不能为空");
        }
        if (loginUser.getPassword() == null || "".equals(loginUser.getPassword())) {
            throw new RuntimeException("密码不能为空");
        }
        if (!"123".equals(loginUser.getPassword())) {
            throw new RuntimeException("用户名或密码不正确");
        }

        System.out.println("用户名密码正确，校验通过");
    }
}
