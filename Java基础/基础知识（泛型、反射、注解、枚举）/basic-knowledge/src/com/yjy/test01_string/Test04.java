package com.yjy.test01_string;

public class Test04 {

	public static void main(String[] args) {
		StringBuffer a = new StringBuffer("A");
		StringBuffer b = new StringBuffer("B");
		operate(a, b);
		System.out.println(a + "." + b); //
	}

	private static void operate(StringBuffer x, StringBuffer y) {
		x.append(y);
		y = x;
	}
}
// 结果为“AB.B”
// 解析：
// 1、刚调用operate方法时，引用a、x指向对象A，而引用b、y指向对象B；
// 2、执行“x.append(y)”后，引用x指向的对象A被连接了B，对象A也就变成了AB。此时，引用a、引用x指向对象AB，而引用b、引用y指向对象B；
// 3、执行“y=x”后，引用a、x、y指向对象AB，而引用b没有发生任何改变，仍然指向对象B。
