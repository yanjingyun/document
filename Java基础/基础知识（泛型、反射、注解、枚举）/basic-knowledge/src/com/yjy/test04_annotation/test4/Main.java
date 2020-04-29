package com.yjy.test04_annotation.test4;

import java.lang.reflect.Field;

// ±‰¡ø◊¢Ω‚≤‚ ‘
public class Main {

	@MyAnnotation(value = "testField")
	private String str;
	
	public String getStr() {
		return str;
	}

	public static void main(String[] args) throws NoSuchFieldException {
		Field field = Main.class.getDeclaredField("str");
		MyAnnotation annotation = field.getAnnotation(MyAnnotation.class);
		System.out.println("value=" + annotation.value());
		
	}
}
