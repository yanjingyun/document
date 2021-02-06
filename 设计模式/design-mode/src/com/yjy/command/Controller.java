package com.yjy.command;


public class Controller {
    private Command okCommand;
    private Command verticalCommand;
    private Command horizontalCommand;

    // 绑定OK键命令
    public void bindOKCommand(Command okCommand) {
        this.okCommand = okCommand;
    }

    // 绑定上下方向键命令
    public void bindVerticalCommand(Command verticalCommand) {
        this.verticalCommand = verticalCommand;
    }

    // 绑定左右方向键命令
    public void bindHorizontalCommand(Command horizontalCommand) {
        this.horizontalCommand = horizontalCommand;
    }

    // 开始按键映射命令
    public void buttonOKHold() {
        System.out.print("长按OK按键……");
        okCommand.exe();
    }

    public void buttonOKClick() {
        System.out.print("单击OK按键……");
        okCommand.unexe();
    }

    public void buttonUpClick() {
        System.out.print("单击↑按键……");
        verticalCommand.exe();
    }

    public void buttonDownClick() {
        System.out.print("单击↓按键……");
        verticalCommand.unexe();
    }

    public void buttonLeftClick() {
        System.out.print("单击←按键……");
        horizontalCommand.unexe();
    }

    public void buttonRightClick() {
        System.out.print("单击→按键……");
        horizontalCommand.exe();
    }
}
