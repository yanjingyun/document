package com.yjy.command;

// 策略模式的基础上又增加一层中间模块，开始编写命令模块代码，首先是命令接口。
public interface Command {
    //执行命令操作
    public void exe();

    //反执行命令操作
    public void unexe();
}
