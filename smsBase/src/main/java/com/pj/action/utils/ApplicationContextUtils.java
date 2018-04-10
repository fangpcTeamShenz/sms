package com.pj.action.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

final public class ApplicationContextUtils {
	/**
	 * 由于applicationContext.xml 的唯一性,在这里可以把它写成单例模式
	 */
	private static ApplicationContext applicationContext = null;

	private ApplicationContextUtils() {
	} // 空的构造方法

	static {
		/**
		 * static 静态代码快,只在该类被加载时,执行一次
		 */
		applicationContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static <T> T getBean(Class<T> classOfT) {
		String clazzName = classOfT.getName();
		return (T) ApplicationContextUtils.getApplicationContext().getBean(
				toLowerCaseFirstOne(clazzName.substring(
						clazzName.lastIndexOf('.') + 1, clazzName.length())));
	}

	// 首字母转小写
	private static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder())
					.append(Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1)).toString();
	}
}
