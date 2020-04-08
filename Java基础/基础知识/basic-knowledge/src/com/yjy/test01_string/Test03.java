package com.yjy.test01_string;

public class Test03 {
	static {
		int x = 5;
	}

	static int x, y;

	public static void main(String[] args) {
		x--;
		myMethod();
		System.out.println(x + y + ++x);
	}

	public static void myMethod() {
		y = x++ + ++x;
	}
}
// 输出结果为3
// 解析：
// 1、静态语句中x为局部变量，不影响静态变量x的值
// 2、x和y为静态变量，默认初始值为0，属于当前类，其值改变会影响整个类的运行
// main方法中：
// 1）执行 x-- 后，x = -1
// 2）执行 myMethod() 后，x = 1， y = (-1 + 1) = 0
// 3）执行 x + y + ++x 后，先执行 x+y结果为1，再执行++x结果为2，最终结果为3