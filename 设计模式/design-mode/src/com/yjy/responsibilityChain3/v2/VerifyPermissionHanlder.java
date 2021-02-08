package com.yjy.responsibilityChain3.v2;

public class VerifyPermissionHanlder extends Handler {
    @Override
    public void doHandler(LoginUser loginUser) {
        if (!"admin".equals(loginUser.getPermission())) {
            System.out.println("该权限无法访问");
            return;
        }
        System.out.println("该权限允许访问，校验通过");
//        doNextHandler(loginUser);
    }
}
