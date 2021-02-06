package com.yjy.command;


public class ClientTest {
    public static void main(String[] args) {
//        // 测试1
//        System.out.println("===客户端用【电线】直接操作灯泡===");
//        Bulb bulb = new Bulb();
//        bulb.on();
//        bulb.off();
        /* 打印(这种类似与策略模式)：
            ===客户端用【电线】直接操作灯泡===
            通电，灯亮...
            断电，灯灭...
         */
        // 结论：直接用导线给通电了，简单粗暴，虽然没有错，但这看上去与设计模式没有任何瓜葛。


//        // 测试2
//        System.out.println("===客户端用【开关】操作电器===");
//        Switcher switcher = new Switcher();
//        switcher.setSwitchable(new Bulb());//灯泡接入开关。
//        switcher.buttonOnClick();
//        switcher.buttonOffClick();
//        switcher.setSwitchable(new Fan());//风扇接入开关。
//        switcher.buttonOnClick();
//        switcher.buttonOffClick();
        /* 打印：
            ===客户端用【开关】操作电器===
            按下开按钮...
            通电，灯亮...
            按下关按钮...
            断电，灯灭...
            按下开按钮...
            通电，风扇转动...
            按下关按钮...
            断电，风扇停止...
         */
        // 结论：Switcher类只能是Switchable设备对象，这就与”可开关设备接口“强耦合了，也就是说它只能控制“灯泡或风扇”，并不能控制”电视或收音机”。


    }
}
