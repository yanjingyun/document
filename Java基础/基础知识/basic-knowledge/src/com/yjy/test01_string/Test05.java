package com.yjy.test01_string;

public class Test05 {

	class Super {
		int flag = 1;

		Super() {
			test();
		}

		void test() {
			System.out.println("Super.test() flag=" + flag);
		}
	}

	class Sub extends Super {
		Sub(int i) {
			flag = i;
			System.out.println("Sub.Sub()flag=" + flag);
		}

		void test() {
			System.out.println("Sub.test()flag=" + flag);
		}
	}

	public static void main(String[] args) {
		new Test05().new Sub(5);
	}
}
// 输出：
// Sub.test()flag=1
// Sub.Sub()flag=5
// 解析：
// 1、首先调用父类构造方法，即super()
// 2、调用test()方法。
// 3、由于在子类sub()中重写了test()方法，所以调用子类test()
// 4、输出Sub.test() flag=1
// 5、调用sub的有参构造方法
// 6、输出Sub.Sub() flag=5
// 重点：要时刻记得子类重写父类方法，调用时会调用子类重写之后的方法