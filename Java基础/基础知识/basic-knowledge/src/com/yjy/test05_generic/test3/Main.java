package com.yjy.test05_generic.test3;

import java.awt.List;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.yjy.test05_generic.test2.User;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = User.class.getMethod("setRoles", List.class);
		Type[] parameterTypes = method.getGenericParameterTypes(); // 获取带泛型的参数类型列表
		for (Type parameterType : parameterTypes) {
			if (parameterType instanceof ParameterizedType) {
				ParameterizedType type = (ParameterizedType) parameterType;
				Type[] typeArguments = type.getActualTypeArguments();
				for (Type typeArgument : typeArguments) {
					System.out.println("parameterArgClass = " + typeArgument);
				}
			}
		}
	}
}
