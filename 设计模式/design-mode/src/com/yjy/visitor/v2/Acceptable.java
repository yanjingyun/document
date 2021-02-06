package com.yjy.visitor.v2;


public interface Acceptable {
    // 主动接受拜访者
    void accept(Visitor visitor);
}