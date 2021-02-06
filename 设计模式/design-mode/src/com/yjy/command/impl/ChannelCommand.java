package com.yjy.command.impl;

import com.yjy.command.Command;
import com.yjy.command.Device;

/**
 * Tilte: ChannelCommand
 * Description:
 * Copyright:  Copyright（c）2021 版本
 * Company:E-Limen
 *
 * @Author: yanjy
 * @Date: 2021-02-02 10:19
 * @Vsersion v1.0.0
 **/
public class ChannelCommand implements Command {
    private Device device;
    public ChannelCommand(Device device) {
        this.device = device;
    }

    @Override
    public void exe() {
        device.channelUp();
    }

    @Override
    public void unexe() {
        device.channelDown();
    }
}
