package com.abrathod.common;

import java.lang.reflect.Field;

import java.util.Date;

public class PrePersistUtil {

	public static void pre(Object object) {

		try {
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);

				if (field.getType().getName().equals("java.lang.String") && !field.getName().equals("toString")
						&& field.get(object) != null) {
					field.get(object).toString().replaceAll("&", "AND");
					field.set(object, new HTMLFilter().filter(field.get(object).toString()));
				}
				if ((field.getName().equals("startDate") || field.getName().equals("creationDate"))
						&& field.getType().getName().equals("java.util.Date") && field.get(object) == null) {
					field.set(object, new Date());
				}
				if (field.getName().equals("lastUpdateDate") && field.getType().getName().equals("java.util.Date")) {
					field.set(object, new Date());
					// System.out.println(" lastUpdateDate ");
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}