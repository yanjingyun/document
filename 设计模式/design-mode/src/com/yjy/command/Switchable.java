package com.yjy.command;

/**
 * 电器接口
 */
public interface Switchable {
    /**
     * 通电
     */
    void on();

    /**
     * 断电
     */
    void off();
}
