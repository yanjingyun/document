package com.yjy.responsibilityChain2.v1;

/**
 * 步骤2：创建校验处理器
 * 登录校验（验证码校验 -> 账号密码判断 -> 用户重复判断 -> …）
 */
public abstract class Handler {
    protected Handler next = null;

    public void next(Handler handler) {
        this.next = handler;
    }

    public abstract void doHandler(User user);

    protected void doNextHandle(User user) {
        if (next != null) {
            next.doHandler(user);
        }
    }
}
