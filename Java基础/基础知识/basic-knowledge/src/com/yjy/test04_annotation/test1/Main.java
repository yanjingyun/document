package com.yjy.test04_annotation.test1;

import java.lang.annotation.Annotation;

// 类注解测试
@MyAnnotation(value="测试注解")
public class Main {

	public static void main(String[] args) {
		Annotation[] annotations = Main.class.getAnnotations(); //方式1：获取该类注解
		for (Annotation annotation : annotations) {
			if (annotation instanceof MyAnnotation) {
				MyAnnotation myAnnotation = (MyAnnotation) annotation;
				System.out.println(myAnnotation.value());
			}
		}
		
		MyAnnotation annotation = Main.class.getAnnotation(MyAnnotation.class); //方式2：获取该类注解
		System.out.println(annotation.value());
	}
}
