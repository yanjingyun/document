package com.yjy.command.impl;

import com.yjy.command.Command;
import com.yjy.command.Device;

public class SwitchCommand implements Command {
    private Device device;// 此处持有高级设备接口。

    public SwitchCommand(Device device) {
        this.device = device;
    }
    @Override
    public void exe() {
        device.on();// 执行命令调用开机操作
    }

    @Override
    public void unexe() {
        device.off();// 反执行命令调用关机操作
    }
}
