package com.yjy.responsibilityChain2.v1;

/**
 * 认证校验-校验用户名密码是否正确
 */
public class AuthHandler extends Handler {
    @Override
    public void doHandler(User user) {
        if (checkUsernameAndPassword(user.getUsername(),user.getPassword())) {
            System.out.println("用户名密码正确，校验通过");
            doNextHandle(user);
        } else {
            System.out.println("用户名或密码错误，校验不通过");
        }
    }

    private boolean checkUsernameAndPassword(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }
}
