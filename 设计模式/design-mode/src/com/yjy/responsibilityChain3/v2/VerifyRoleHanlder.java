package com.yjy.responsibilityChain3.v2;

public class VerifyRoleHanlder extends Handler {
    @Override
    public void doHandler(LoginUser loginUser) {
        if (!"admin".equals(loginUser.getRoleName())) {
            System.out.println("该角色无法访问");
            return;
        }
        System.out.println("该角色允许访问，校验通过");
//        doNextHandler(loginUser);
    }
}
