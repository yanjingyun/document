package com.yjy.responsibilityChain3.v3;

public class VerifyPermissionHanlder extends Handler {
    @Override
    public void doHandler(LoginUser loginUser) {
        if (!"admin".equals(loginUser.getPermission())) {
            throw new RuntimeException("该权限无法访问");
        }
        System.out.println("该权限允许访问，校验通过");
    }
}
