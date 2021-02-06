package com.yjy.command;

/**
 * 控制开关
 */
public class Switcher {

    // 此开关与灯耦合，无法替换为其它电器
//    private Bulb bulb = new Bulb();

    // 此开关与电器接口耦合，可任意替换电器。
    private Switchable switchable;

    // 替换电器方法
    public void setSwitchable(Switchable switchable) {
        this.switchable = switchable;
    }

    // 按键事件绑定
    public void buttonOnClick() {
        System.out.println("按下开按钮...");
        switchable.on();
    }
    public void buttonOffClick() {
        System.out.println("按下关按钮...");
        switchable.off();
    }
}
