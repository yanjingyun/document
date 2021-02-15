package com.yjy.strategy.v1;

/**
 * 计算器实现-原始写法
 * 缺点：我们往后的扩展想想，如果随着我们的算法不断增加，如乘法、除法、次方、开方等等，那么这个计算器类就得不断的改啊改啊，每次升级算法我们都要把机器给拆开然后更改类代码，这岂不是作死？
 */
public class Caculate { // 加减乘除都写在一起，违反了设计模式原则的做法
    public int add(int a, int b) { //加法
        return a + b;
    }

    public int sub(int a, int b) { // 减法
        return a - b;
    }
}
