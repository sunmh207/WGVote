package com.stanley.common;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class JitongConstants {
	/**
	 * session中存放hql语句，用于导出excel
	 */
	public static final String SESSION_OBJECT="SESSION_OBJECT";
	public static final int MAX_PAGE_SIZE=20000;
	
	public static final String USER = "user";
	public static final String JT_DATE_FORMAT="yyyy-MM-dd"; 
	public static final String JT_DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	
	
	public static final String STRING_TRUE="1";
	public static final String STRING_FALSE="0";
	
	public static final String[] HOUR_ARRAY={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	public static final String[] MINUTE_ARRAY={"00","05","10","15","20","25","30","35","40","45","50","55"};

	public static ConfigurableApplicationContext context;
	/**
	 * super user name
	 */
	public static String ADMIN="admin";
	public static String PASSWORD="123";
	public static String lisence="123";
	
	
	
	public static Properties properties = null;
	public static void loadProps() {
		properties = new Properties();
		InputStream in = null;
		try {
			in = JitongConstants.class.getResourceAsStream("/config.properties");
			properties.load(in);
			ADMIN = getProp("ADMIN", "admin");
			PASSWORD = getProp("PASSWORD", "123");
			lisence = getProp("lisence", "123");
		} catch (Exception ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getProp(String name, String defaultValue) {
		if (properties == null) {
			return null;
		}
		String property = null;
		try {
			property = new String(properties.getProperty(name).getBytes("ISO-8859-1"), "gb2312");
		} catch (UnsupportedEncodingException e) {
			property = properties.getProperty(name);
		} catch (Exception e) {
			property = properties.getProperty(name);
		}

		if (property == null) {
			return defaultValue;
		}
		return property.trim();
	}

	public static void init() {
		loadProps();
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

}
