package com.yjy.test03_reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

// 测试属性
public class Test2 {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.yjy.test03_reflect.Student"); //获取类
		Student student = (Student) clazz.newInstance(); //实例化对象
		
		Field stuNoField = clazz.getDeclaredField("stuNo"); //获取特定字段
		stuNoField.setAccessible(true);
		stuNoField.set(student, 23);
		
		Field[] fields = clazz.getFields(); //获取“本类及父类”所有公共字段
		for (Field field : fields) {
			System.out.println("变量名称：" + field.getName());
			System.out.println("修饰符：" + Modifier.toString(field.getModifiers()));
			System.out.println("变量类型：" + field.getType());
		}
		
		Field[] fields2 = clazz.getDeclaredFields(); //获取本类所有字段
		for (Field field : fields2) {
			System.out.println("变量名称：" + field.getName());
			System.out.println("修饰符：" + Modifier.toString(field.getModifiers()));
			System.out.println("变量类型：" + field.getType());
		}
	}
}