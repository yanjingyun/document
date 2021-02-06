package com.yjy.responsibilityChain2.v2;

/**
 * 步骤2：创建校验处理器
 * 登录校验（用户判断 -> 账号密码判断 -> 验证码校验-> …）
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

    public static class Builder {
        private Handler header = null;
        private Handler tail = null;

        public Builder add(Handler handler) {
            if (this.header == null) { // 第一个
                this.header = this.tail = handler;
            } else {
                tail.next = handler;
                tail = handler;
            }
            return this;
        }

        public Handler build() {
            return this.header;
        }
    }
}
