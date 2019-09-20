package com.manage.util;

import net.sf.json.JSONObject;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

public class InterFaceUtils {
	/**
	 * 输入参数
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static String inputJson(HttpServletRequest request,
			HttpServletResponse response) {
		String json = "";
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) request.getInputStream(), "utf-8"));
			StringBuffer sb = new StringBuffer("");
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			json = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 输出参数
	 * 
	 * @param request
	 * @param response
	 * @param jsonMap
	 */
	public static void outputJson(HttpServletRequest request,
                                  HttpServletResponse response, Map<String, Object> jsonMap) {
		try {
			response.setContentType("application/json;charset=utf-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			JSONObject resultJSON = JSONObject.fromObject(jsonMap);
			String result = resultJSON.toString();
			result = result.replaceAll("\n", "");
			PrintWriter out = response.getWriter();
			// 打印
			// System.out.println(result);
			out.println(result);
			// 输出流
			out.flush();
			// 关闭流
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
