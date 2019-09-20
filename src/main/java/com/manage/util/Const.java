package com.manage.util;

import java.util.HashMap;
import java.util.Map;

public class Const {

	/**
	 * 初始化密码
	 */
	public static final String INIT_PASS = "123456";

	/**
	 * 图片前缀
	 */
	public static final String image_prefix = "data:image/png;base64,";

	/**
	 * 接口前缀
	 */
	public static final String interface_prefix = "if_";

	public static final String CODE = "code";

	public static final String DATA = "data";

	public static final String TOTAL = "total";

	public static final String TOKEN = "token";

	public static final String MESSAGE = "message";

	public static final String SUCCESS_CODE = "0001";

	public static final String FAIL_CODE = "0002";

	/**
	 * CodeTypeMap
	 * 
	 * @return
	 */
	public static final Map<String, String> CodeTypeMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("1", "托");
			put("2", "箱");
			put("3", "瓶");
		}
	};

	/**
	 * CodeStateMap
	 * 
	 * @return
	 */
	public static final Map<String, String> CodeStateMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("1", "未使用");
			put("2", "已使用");
		}
	};

	/**
	 * DriedSweetMap
	 * 
	 * @return
	 */
	public static final Map<String, String> DriedSweetMap = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("Dry", "1");
			put("Semi-dry", "2");
			put("Sweet", "3");
			put("Semi-sweet", "4");
		}
	};
	
	
	
}