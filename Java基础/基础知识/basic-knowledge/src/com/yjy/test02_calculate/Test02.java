package com.yjy.test02_calculate;

public class Test02 {

	public static void main(String[] args) {
//		byte b1 = 1, b2 = 2, b3, b6, b8;
//		final byte b4 = 4, b5 = 5, b7;
//		b3 = (b1 + b2); // 编译错误
//		b6 = b4 + b5;
//		b8 = (b1 + b4); // 编译错误
//		b7 = (b2 + b5); // 编译错误
	}
}

/*
说明：

“+、-、*、/”运算时数值类型转换：
	自动数据类型转换(由低到高)：byte,short,char-> int -> long -> float -> double

当使用“+、-、*、%”运算操作时，遵循如下规则：
	-1）只要两个操作数中有一个是double类型的，另一个将会被转换成double类型，并且结果也是double类型；
	-2）如果两个操作数中有一个是float类型的，另一个将会被转换为float类型，并且结果也是float类型；
	-3）如果两个操作数中有一个是long类型的，另一个将会被转换成long类型，并且结果也是long类型；
	-4）否则（操作数为：byte、short、int 、char），两个数都会被转换成int类型，并且结果也是int类型。
*/
