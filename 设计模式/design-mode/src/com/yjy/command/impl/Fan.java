package com.yjy.command.impl;

import com.yjy.command.Switchable;

/**
 * 风扇类
 */
public class Fan implements Switchable {
    @Override
    public void on() {
        System.out.println("通电，风扇转动...");
    }

    @Override
    public void off() {
        System.out.println("断电，风扇停止...");
    }
}
