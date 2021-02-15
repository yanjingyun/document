package com.yjy.responsibilityChain3.v3;

public abstract class AbstractHandler {
    protected AbstractHandler next; // 责任链中下一个元素

    public void next(AbstractHandler abstractHandler) {
        this.next = abstractHandler;
    }

    public void handle(LoginUser loginUser) {

        try {
            // 写法1
//            Handler curentHandler = this;
//            do {
//                curentHandler.doHandler(loginUser);
//                curentHandler = curentHandler.next;
//            } while (curentHandler != null);

            // 写法2
            doHandler(loginUser);
            if (next != null) { // 是否有下一个校验类节点
                next.handle(loginUser);
            }
        } catch (Exception e) {
            System.out.println("校验不通过：" + e.getMessage());
        }
    }

    /**
     * 逻辑校验
     * @param loginUser
     */
    protected abstract void doHandler(LoginUser loginUser);

    public static class Builder {
        private AbstractHandler header;
        private AbstractHandler tail;

        public Builder add(AbstractHandler abstractHandler) {
            if (this.header == null) { // 第一个
                this.header = this.tail = abstractHandler;
            } else {
                tail.next = abstractHandler;
                tail = abstractHandler;
            }
            return this;
        }

        public AbstractHandler build() {
            return this.header;
        }
    }
}
