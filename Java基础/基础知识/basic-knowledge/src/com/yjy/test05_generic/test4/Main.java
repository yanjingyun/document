package com.yjy.test05_generic.test4;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.yjy.test05_generic.test2.User;

public class Main {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException  {
		Field field = User.class.getDeclaredField("roles");
		Type genericType = field.getGenericType(); // 获取带泛型的变量类型
		if (genericType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) genericType;
			Type[] typeArguments = type.getActualTypeArguments();
			for (Type typeArgument : typeArguments) {
				System.out.println("parameterArgClass = " + typeArgument);
			}
		}
	}
}
