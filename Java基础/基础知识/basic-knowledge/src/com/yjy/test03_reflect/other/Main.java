package com.yjy.test03_reflect.other;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * 获取类及父类所有私有属性：<br>
 * 将一些公共字段全部写道一个公共类里，业务类只需继承对应的公共类即可引入这些公共字段。<br>
 * 如User类继承了AudutEntity类，它就有了“创建人、创建时间、最后修改人、最后修改时间、版本号、id”这些公共字段。
 */
public class Main {

	public static void main(String[] args) {
		User user = new User();
		user.setUsername("username!!");
		user.setPassword("password!!");
		user.setBirthday(Date.valueOf("2019-12-02"));

		user.setCreateUser("createUser!!");
		user.setLastUpdateUser("lastUpdateUser!!");
		user.setCreateTime(Timestamp.valueOf("2019-12-03 08:23:26"));
		user.setLastUpdateTime(Timestamp.valueOf("2019-12-03 08:23:26"));

		user.setVersionNumber(2);
		user.setId(UUID.randomUUID().toString());
		getAllField(user);
	}

	private static void getAllField(Object obj) {
		Class<?> clazz = obj.getClass();
		try {
			while (clazz != Object.class) {
				System.out.println("类名称:" + clazz);

				Field[] declaredFields = clazz.getDeclaredFields();
				for (Field field : declaredFields) {
					int mod = field.getModifiers();
					if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
						clazz = clazz.getSuperclass();
						continue;
					}
					field.setAccessible(true);
					System.out.println("字段名" + field.getName() + "=" + field.get(obj));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
