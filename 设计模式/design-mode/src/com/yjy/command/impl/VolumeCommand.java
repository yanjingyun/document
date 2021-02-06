package com.yjy.command.impl;

import com.yjy.command.Command;
import com.yjy.command.Device;

public class VolumeCommand implements Command {
    private Device device;

    public VolumeCommand(Device device) {
        this.device = device;
    }

    @Override
    public void exe() {
        device.volumeUp();
    }

    @Override
    public void unexe() {
        device.volumeDown();
    }
}
