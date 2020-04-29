package com.yjy.test02_calculate;

public class Test01 {

	public static void main(String[] args) {
		int i = 5;
		int s = (i++) + (++i) + (i--) + (--i); // s=5+7+7+5=24
		System.out.println(s); // 24
	}
}
