package com.manage.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 判空工具类
 * @author wangliang0103@hotmail.com
 * @date 2010-1-30 下午10:42:18
 */
public final class Empty {
	

	/**
	 * 判断字符串是否为空或者为null 是返回"0"
	 * 
	 * @param str
	 * @return
	 */
	public static String isEmpty_2(String str) {
		if ( str == null || str.equals("") ) {
			return "0.0000";
		}
		return str;

	}
	
	/**
	 * 判断字符串是否为空或者为null 是返回"";
	 * @param str
	 * @return
	 */
	public static String isEmpty_1(String str) {
		if ( str == null || str.equals("") ) {
			return "";
		}
		return str;

	}
	
	/**
	 * 判断字符串是否为空或者为null 返回真假;
	 * @param str
	 * @return
	 */
	public static boolean isEmpty_3(String str) {
		return str == null || str.length() == 0 || "null".equals(str);
	}	

	/**
	 * 判断字符串是否为空，长度为0被认为是空字符串.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * 判断字符串是否为空，字符串前后空白被截断，截断后长度为0被认为是空字符串.
	 * 
	 * @param str
	 * @param isTrimed
	 *            是否截去空白
	 * @return
	 */
	public static boolean isEmpty(String str, boolean isTrimed) {
		if(isTrimed)
			return str == null || str.trim().length() == 0;
		return isEmpty(str);
	}

	/**
	 * 判断列表是否为空，列表没有元素也被认为是空
	 * 
	 * @param list
	 * @return
	 */
	
	public static boolean isEmpty(java.util.Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}
	
	/**
	 * 判断数组是否为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object [] array){
		return array == null || array.length == 0;
	}
	
	/**
	 * 判断对象是否为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Object object){
		return object == null;
	}
	
	/**
	 * 判断Map是否为空
	 * @param array
	 * @return
	 */
	public static boolean isEmpty(Map<String,Object> object){
		if(object == null) {
			return true;
		}
		return object.isEmpty();
	}
	
	/**
	 * 判断字符串是否为空或者为null 是返回"";
	 *
	 * @param ob
	 * @return
	 */
	public static boolean isNotEmpty(Object ob) {
		if (ob instanceof String) {
			return !isEmpty((String) ob);
		} else if (ob instanceof Object[]) {
			return !isEmpty((Object[]) ob);
		} else if (ob instanceof java.util.Collection<?>) {
			return !isEmpty((java.util.Collection<?>) ob);
		} else {
			return !isEmpty(ob);
		}

	}
	
	public static void main(String args[]) {
		Map<String,Object> a = new HashMap<String,Object>();
		a.put("", "");
		System.out.println(Empty.isEmpty(a));
	}
}
