package com.yjy.test01_string;

public class Test01 {
	public static void main(String[] args) {
		String str1 = "hello";
		String str2 = "java";
		String str3 = "hellojava";
		String str4 = str1 + str2;
		String str5 = str1 + "java";
		System.out.println(str3 == str4); // false 分析：str4是两个引用类型结合而成，它的值无法在编译期确定
		System.out.println(str3 == str5); // true
	}
}
