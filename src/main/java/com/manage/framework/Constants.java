package com.manage.framework;

public class Constants {
	
	public static final String ZH_CN = "zh_CN";
	public static final String EN_US = "en_US";
	public static String HEAD_TOKEN_NAME = "x-older-token";
	public static String HEAD_UID_NAME = "x-user-uid";
	public static String HEAD_PWD_NAME = "x-user-secretkey";
	public static String HEAD_APPID_NAME = "x-appid-appid";
	public static String URL_NAME = "http://apidev.cochain.cn:8080/cochainapi";
	public static String DEFAULT_URL = "http://apidev.cochain.cn:8080/cochainapi";
	
	public static String STATE_0 = "state_0";
	public static String STATE_1 = "state_1";
	public static String STATE_2 = "state_2";
	public static String STATE_3 = "state_3";
	
	public static int ARTIFACT_ID_0 = 0;//商品id未被下载
	public static int ARTIFACT_ID_1 = 1;//被下载
	public static int ARTIFACT_ID_2 = 2;//已上链校验
	
	public static int BASE_INFO_0 = 0;//基础信息未上链
	public static int BASE_INFO_1 = 1;//基础信息上链未确认
	public static int BASE_INFO_2 = 2;//基础信息已上链并确认
	public static int BASE_INFO_3 = 3;//基础信息已上链并确认
	public static int BASE_INFO_4 = 4;//基础信息异常
	
	 
	
	public static int TRANSACTION_INFO_0 = 0;//交易信息未上链
	public static int TRANSACTION_INFO_1 = 1;//交易信息上链未确认
	public static int TRANSACTION_INFO_2 = 2;//交易信息已上链并确认
	public static int TRANSACTION_INFO_3 = 3;//交易信息已上链并确认
	public static int TRANSACTION_INFO_4 = 4;//交易信息异常
	
	public static int LIMIT_MAX = 1000;//一次数据库查询默认最大值
	
	public static int HOME_PAGE_COUNT = 12;//一次数据库查询默认最大值
	public static int DEFAULT_PAGE_COUNT = 50;//一次数据库查询默认最大值
	public static int MAX_BLOCK_COUNT = 12;//一次数据库查询默认最大值
}
