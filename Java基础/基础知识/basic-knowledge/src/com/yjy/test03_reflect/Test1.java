package com.yjy.test03_reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

// 测试反射获取类
public class Test1 {
	public static void main(String[] args) {
		Class<?> clazz = Student.class; //获取本类
		System.out.println(clazz); 
		System.out.println(clazz.getSuperclass()); //获取擦除泛型参数的父类（class com.yjy.test03_reflect.Student）
		
		Type type = clazz.getGenericSuperclass();
		System.out.println(type);//获取父类（class com.yjy.test03_reflect.Person）
		ParameterizedType p = (ParameterizedType) type;
		Class<?> c = (Class<?>)p.getActualTypeArguments()[0]; //class java.lang.String
		System.out.println(c.getName()); //java.lang.String
	}
}