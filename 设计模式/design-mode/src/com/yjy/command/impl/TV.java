package com.yjy.command.impl;

import com.yjy.command.Device;

public class TV implements Device {

    @Override
    public void on() {
        System.out.println("电视机启动");
    }

    @Override
    public void off() {
        System.out.println("电视机关闭");
    }

    @Override
    public void channelUp() {
        System.out.println("电视机频道+");
    }

    @Override
    public void channelDown() {
        System.out.println("电视机频道-");
    }

    @Override
    public void volumeUp() {
        System.out.println("电视机音量+");
    }

    @Override
    public void volumeDown() {
        System.out.println("电视机音量-");
    }
}
