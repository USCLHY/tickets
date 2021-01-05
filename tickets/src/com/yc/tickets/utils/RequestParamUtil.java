package com.yc.tickets.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 封装前端参数工具类
 * @author 外哥
 *
 */
@SuppressWarnings("all")
public class RequestParamUtil {

	/**
	 * 将前端参数封装到对象中
	 * @param <T>
	 * @param cls
	 * @param request
	 * @return
	 */
	public static <T> T getParams(Class<T> cls, HttpServletRequest request) {
		T t = null;
		try {
			// 获取给定的类中的所有setter方法
			Method[] methods = cls.getMethods();

			// 存储类中所有的setter方法，以方法为键，以对应的方法对象为值
			Map<String, Method> setters = new HashMap<String, Method>();

			String methodName = null;
			for (Method method : methods) {

				methodName = method.getName();
				if (methodName.startsWith("set")) {
					setters.put(methodName, method);
				}
			}

			// 获取请求中的所有参数的参数名
			Enumeration<String> names = request.getParameterNames();

			String name = null;
			Method method = null;
			
			// 方法的形参的数据类型数组
			Class<?>[] types = null;
			Class<?> type = null;
			
			// 前端参数
			Object obj = null;
			String objStr = null;
			
			// 实例化这个类的一个对象
			t = cls.newInstance();

			// 循环所有的参数名，找到这个参数注入时对应的setter方法，将这个参数注入到对应的对象的属性中
			while (names.hasMoreElements()) {
				name = names.nextElement();

				obj = request.getParameter(name);
				if (obj == null) {
					// 参数值为null
					continue;
				}

				objStr = String.valueOf(obj);
				if ("".equals(objStr)) {
					// 参数值为""
					continue;
				}

				methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
				
				// 在map集合中找键为methodName的值，如果没有找到则返回null
				method = setters.getOrDefault(methodName, null);
				
				if (method == null) {
					// 说明实体类中没有这个属性的注值方法
					continue;
				}

				// 一位内方法调用跟数据类型有关，所以我们必须先获取这个方法的形参的数据类型列表
				types = method.getParameterTypes();
				if (types == null || types.length <= 0) {
					// 说明这个方法不带形参
					continue;
				}

				type = types[0];

				// 反向激活这个方法注值
				if (Integer.TYPE == type || Integer.class == type) {
					method.invoke(t, Integer.parseInt(objStr));
				} else if (Float.TYPE == type || Float.class == type) {
					method.invoke(t, Float.parseFloat(objStr));
				} else if (Double.TYPE == type || Double.class == type) {
					method.invoke(t, Double.parseDouble(objStr));
				} else {
					method.invoke(t, objStr);
				}

			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return t;
	}
}
