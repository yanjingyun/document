package com.yjy;

import org.junit.Test;

// Lambda表达式
public class LambdaTest {

	@Test
	public void test1() {
		// 有无参数类型
		MathOperation addition = (int a, int b) -> a + b;
		MathOperation subtraction = (a, b) -> a - b;

		// 有花括号，有return关键字
		MathOperation multiplication = (a, b) -> {
			return a * b;
		};
		// 无花括号，无return关键字
		MathOperation division = (a, b) -> a / b;

		System.out.println("加运算：" + operate(10, 5, addition));
		System.out.println("减运算：" + operate(10, 5, subtraction));
		System.out.println("乘运算：" + operate(10, 5, multiplication));
		System.out.println("除运算：" + operate(10, 5, division));
	}

	private int operate(int i, int j, MathOperation mathOperation) {
		return mathOperation.operation(i, j);
	}

	interface MathOperation {
		int operation(int a, int b);
	}

	@Test
	public void test2() {
		// 无括号，单个参数情况
		Greet greet = message -> System.out.println("saying::" + message);
		greet.sayMessage("hello");
		greet.sayMessage("hello2");
	}

	interface Greet {
		void sayMessage(String message);
	}
}
