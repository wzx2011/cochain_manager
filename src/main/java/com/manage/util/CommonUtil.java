package com.manage.util;

import cn.jpush.api.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class CommonUtil {

	/**
	 * 获取项目完整路径
	 */
	public static String getProjectPath(HttpServletRequest request) {
		// 项目目录
		String path = request.getContextPath();
		String scheme = request.getScheme();
		String servername = request.getServerName();
		int serverport = request.getServerPort();

		String basePath = scheme + "://" + servername + ":" + serverport + path
				+ "/";
		return basePath;
	}

	/**
	 * 年月日时分秒
	 */
	public static String getYYYYMMDD() {
		Calendar nowtime = new GregorianCalendar();
		String dateTimeStr = String.format("%04d", nowtime.get(Calendar.YEAR))
				+ String.format("%02d", nowtime.get(Calendar.MONTH) + 1)
				+ String.format("%02d", nowtime.get(Calendar.DATE));
		return dateTimeStr;
	}

	/**
	 * 年月日时分秒
	 */
	public static String getYYYYMMDDhhmmss() {
		Calendar nowtime = new GregorianCalendar();
		String dateTimeStr = String.format("%04d", nowtime.get(Calendar.YEAR))
				+ String.format("%02d", nowtime.get(Calendar.MONTH) + 1)
				+ String.format("%02d", nowtime.get(Calendar.DATE))
				+ String.format("%02d", nowtime.get(Calendar.HOUR_OF_DAY))
				+ String.format("%02d", nowtime.get(Calendar.MINUTE))
				+ String.format("%02d", nowtime.get(Calendar.SECOND));
		return dateTimeStr;
	}

	/**
	 * 年月日时分秒毫秒
	 */
	public static String getYYYYMMDDhhmmssms() {
		Calendar nowtime = new GregorianCalendar();
		String dateTimeStr = String.format("%04d", nowtime.get(Calendar.YEAR))
				+ String.format("%02d", nowtime.get(Calendar.MONTH) + 1)
				+ String.format("%02d", nowtime.get(Calendar.DATE))
				+ String.format("%02d", nowtime.get(Calendar.HOUR_OF_DAY))
				+ String.format("%02d", nowtime.get(Calendar.MINUTE))
				+ String.format("%02d", nowtime.get(Calendar.SECOND))
				+ String.format("%03d", nowtime.get(Calendar.MILLISECOND));
		return dateTimeStr;

	}

	/**
	 * 获取客户端IP
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}

	/**
	 * MySql转义字符过滤
	 */
	public static String EscapeCharacter(String value) {
		if(value != null && !value.equals("")){
			// 单引号
			value = value.replaceAll("\'", "\\\\\'");
			// 双引号
			value = value.replaceAll("\"", "\\\\\"");
		}
		return value;
	}

	public static void main(String[] args) {
		System.out.println(getYYYYMMDD());
		System.out.println(getYYYYMMDDhhmmss());
		System.out.println(getYYYYMMDDhhmmssms());
		System.out.println(EscapeCharacter("na'm\"e"));
	}

}
