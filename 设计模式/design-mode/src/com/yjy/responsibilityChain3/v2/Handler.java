package com.yjy.responsibilityChain3.v2;

public abstract class Handler {
    protected Handler next;

    public void next(Handler handler) {
        this.next = handler;
    }

    public void handle(LoginUser loginUser) {
        Handler curentHandler = this;
        do {
            curentHandler.doHandler(loginUser);
            curentHandler = curentHandler.next;
        } while (curentHandler != null);
    }

    public abstract void doHandler(LoginUser loginUser);

    public static class Builder {
        private Handler header;
        private Handler tail;

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
