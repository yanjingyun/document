package com.yjy.responsibilityChain3.v3;

public class VerifyRoleHanlder extends Handler {
    @Override
    protected void doHandler(LoginUser loginUser) {
        if (!"admin".equals(loginUser.getRoleName())) {
            throw new RuntimeException("该角色无法访问");
        }
        System.out.println("该角色允许访问，校验通过");
    }
}
