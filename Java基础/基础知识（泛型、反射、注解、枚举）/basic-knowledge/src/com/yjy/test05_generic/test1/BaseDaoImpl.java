package com.yjy.test05_generic.test1;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class BaseDaoImpl<T> implements BaseDao<T> {

	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		Type type = getClass().getGenericSuperclass(); //获取带泛型的父类！
		if (type instanceof ParameterizedType) {
			clazz = (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		}
	}
	
	public Class<T> getClazz() {
		return clazz;
	}
}
