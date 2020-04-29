package com.yjy.test03_reflect;

import java.lang.reflect.Method;

// 测试属性
public class Test3 {
	public static void main(String[] args) throws Exception {
		Class<?> clazz = Class.forName("com.yjy.test03_reflect.Student"); //获取类
		Student student = (Student) clazz.newInstance(); //实例化对象
		
		Method declaredMethod = clazz.getDeclaredMethod("setStuNo", Integer.class);
		declaredMethod.invoke(student, 123);
		
		Method[] methods = clazz.getMethods(); //获取“本类及父类”所有公共method
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		System.out.println("---分割线---");
		Method[] methods2 = clazz.getDeclaredMethods(); //获取本类的所有方法（注意父类跟子类写在同一文件中不太一样）
		for (Method method : methods2) {
			System.out.println(method.getName());
		}
	}
}