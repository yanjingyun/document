package com.yjy.test04_annotation.test2;

import java.lang.reflect.Method;

// ·½·¨×¢½â²âÊÔ
public class Main {

	@MyAnnotation(name="doSomethingName", value="doSomethingValue")
	public void doSomething() {
		System.out.println("doSomething()...");
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = Main.class.getMethod("doSomething");
		MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
		System.out.println(annotation.name());
		System.out.println(annotation.value());
	}
}
