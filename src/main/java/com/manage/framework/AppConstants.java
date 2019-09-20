package com.manage.framework;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class AppConstants {
	public static Properties props = getProperties();

	/**
	 * 获取配置文件
	 *
	 * @return 配置Props
	 */
	public static Properties getProperties() {
		// 读取配置文件
		Resource resource = new ClassPathResource("/config/application.properties");
		Properties props = new Properties();
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}

	/**
	 * 根据key值在“application.properties”资源文件中查询对应的值，<br>
	 * application.properties文件中定义了系统将要用到的全局性常量。
	 * @param key
	 *          关键字
	 * @return
	 */
	public static String getValue(String key) {
		// 读取配置文件
		Resource resource = new ClassPathResource("/config/application.properties");
		Properties props = new Properties();
		try {
			return PropertiesLoaderUtils.loadProperties(resource).getProperty(key);
		} catch (Exception e) {
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(AppConstants.getValue("tokenUrl"));
	}
}
