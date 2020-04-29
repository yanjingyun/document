package com.yjy.test04_annotation.test3;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

// ²ÎÊý×¢½â²âÊÔ
public class Main {

	public void doSomething(@MyAnnotation(value="strValue") String text) {
		System.out.println("doSomething()...");
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = Main.class.getMethod("doSomething", String.class);
		
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();

		int i = 0;
		Class<?>[] parameterTypes = method.getParameterTypes();
		for (Annotation[] annotations : parameterAnnotations) {
			Class<?> parameterType = parameterTypes[i++];
			for (Annotation annotation : annotations) {
				if (annotation instanceof MyAnnotation) {
					MyAnnotation myAnnotation = (MyAnnotation) annotation;
					System.out.println("param=" + parameterType.getName());
					System.out.println("value=" + myAnnotation.value());
				}
			}
		}
	}
}
