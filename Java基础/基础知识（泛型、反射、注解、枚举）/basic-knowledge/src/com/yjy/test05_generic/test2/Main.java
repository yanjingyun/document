package com.yjy.test05_generic.test2;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class Main {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException {
		Method method = User.class.getMethod("getRoles");
		Type returnType = method.getGenericReturnType(); //获取带泛型的返回类型
		if (returnType instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) returnType;
			Type[] typeArguments = type.getActualTypeArguments();
			for (Type typeArgument : typeArguments) {
				System.out.println("typeArgument = " + typeArgument);
			}
		}
	}
}
