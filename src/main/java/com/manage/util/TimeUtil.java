package com.manage.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

	private static long hour = 0;
	private static long day = 0;
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat df2 = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat df3 = new SimpleDateFormat("MM/dd/yyyy");
	private static SimpleDateFormat df4 = new SimpleDateFormat("yyyy年M月d日 HH:mm:ss");
	private static SimpleDateFormat df5 = new SimpleDateFormat("MMM dd y, HH:mm:ss",Locale.ENGLISH);
	
	
	public TimeUtil() {

	}

	/**
	 * 毫秒转换成年月日格式
	 * 
	 * @param 毫秒
	 * @return String
	 */
	public static String getHMS(String millisecond) throws NumberFormatException {
		if (Empty.isEmpty(millisecond)) {
			return "";
		} else if (millisecond.equals("0")) {
			return "0";
		}
		long time = Long.parseLong(millisecond);
		day = time / (1000 * 60 * 60 * 24);
		if ((time - day * 1000 * 60 * 60) >= 0) {
			hour = (time - day * 1000 * 60 * 60 * 24) / (1000 * 60 * 60);
		}

		if (day == 0 && hour == 0)
			return "1时";

		if (day == 0 && hour != 0)
			return String.valueOf(hour) + "时";

		return String.valueOf(day) + "天" + String.valueOf(hour) + "时";
	}

	/**
	 * 日期格式转换成字符串日期格式
	 * 
	 * @param Date类型日期
	 * @return String
	 */
	public static String getYMD(Date date) {
		if (date != null) {
			return df.format(date);
		}
		return "";
	}
	
	
	/**
	 * 日期格式转换成字符串日期格式
	 * 
	 * @param Date类型日期
	 * @return String
	 */
	public static String getGlobalTime(Date date) {
		if (date != null) {
			return df5.format(date);
		}
		return "";
	}
	/**
	 * 获取年月日
	 * 
	 * @param Date类型日期
	 * @return String
	 */
	public static String getnyr(Date date) {
		if (date != null) {
			return df4.format(date);
		}
		return "";
	}

	public static String getYMD2(Date date) {
		if (date != null) {
			return df2.format(date);
		}
		return "";
	}

	/**
	 * 字符串日期格式转换成字符串日期格式
	 * 
	 * @param 字符串类型日期
	 * @return String
	 */
	public static String getYMD(String strDate) {
		if (!Empty.isEmpty(strDate)) {
			return df.format(strDate);
		}
		return "";
	}

	/**
	 * 字符串日期格式转换成日期格式
	 * 
	 * @param 字符串类型日期
	 * @return Date
	 */
	public static Date strParseDate(String strDate) {
		if (!Empty.isEmpty(strDate)) {
			try {
				return df.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 字符串日期格式转换成日期格式
	 * 
	 * @param 字符串类型日期
	 * @return Date
	 */
	public static Date strParseDateWithHMS(String strDate) {
		if (!Empty.isEmpty(strDate)) {
			try {
				return df1.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 字符串日期格式转换成日期格式
	 *
	 * @param mm/dd/yyyy类型字符串类型日期
	 * @return Date
	 */
	public static Date strParseDateWithMDY(String strDate) {
		if (!Empty.isEmpty(strDate)) {
			try {
				return df3.parse(strDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获得当前日期yyyy-MM-dd //字符串日期
	 * 
	 * @return String
	 */
	public static String getCurrentStrDate() {
		return df.format(new Date());
	}

	/**
	 * 获得当前日期yyyy-MM-dd HH:mm:ss //字符串日期
	 * 
	 * @return String
	 */
	public static String getYMDWithHms(Date date) {
		if (date != null) {
			return df1.format(date);
		}
		return "";
	}

	/**
	 * 获得当前日期yyyyMMdd //字符串日期
	 * 
	 * @return String
	 */
	public static String getCurrentStr2Date() {
		// long i = System.currentTimeMillis() - 3600*1000*24*4;
		// Date s = new Date(i);
		// return df2.format(s);

		return df2.format(new Date());
	}

	/**
	 * 获得当前日期yyyy-MM-dd //Date类型日期
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return strParseDate(df.format(new Date()));
	}

	/**
	 * 获得当前月份
	 */
	public static int currentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 通过年月日获得毫秒
	 */

	public static long getTimestamp(String date) {
		Date date2 = null;
		try {
			date2 = df1.parse(date);// 将参数按照给定的格式解析参数
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date2.getTime();
	}

	/**
	 * 计算日期差
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static long getDaysBetween(Date startDate, Date endDate) {

		long one_day = 24 * 60 * 60 * 1000;
		long stime = startDate.getTime();
		long etime = endDate.getTime();
		long between = (etime - stime) / one_day;
		return between;
	}

	public static Date getDaysAfterToday(String days) {
		if (Empty.isEmpty(days))
			days = "0";
		return new Date(System.currentTimeMillis() + Integer.valueOf(days) * 24 * 60 * 60 * 1000);
	}

	public static void main(String args[]) {
		String timeStr = "2018-12-10 00:00:00";
		Date date = TimeUtil.strParseDate(timeStr);
		long daysBetween = TimeUtil.getDaysBetween(getCurrentDate(),date);
		System.out.println("当前时间差："+(daysBetween<0));

	}
}
