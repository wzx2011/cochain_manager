package com.manage.util;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeiXinUtil {
	
	private static String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx9a3fe3442a01a59d&secret=2ffb8ad791fb7d7f392d9f6981ecb147&grant_type=authorization_code&code=";
	private static String USER_INFO = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN&access_token=";
	static Logger logger = Logger.getLogger(WeiXinUtil.class);
	public static void getWxUser(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		JSONObject jo = WeiXinUtil.getArtifactId(ACCESS_TOKEN+code);
		System.out.println("access_token"+jo);
		logger.info("access_token"+jo);
		
		String accesstoken = jo.getString("access_token");
		String openid = jo.getString("openid");
		JSONObject user = WeiXinUtil.getArtifactId(USER_INFO+accesstoken+"&openid="+openid);
		System.out.println("user="+user);
		logger.info("user="+user);
		
		
	}
	
	/**
	 * @param urlString
	 * @return
	 */
	
	public static JSONObject getArtifactId(String urlString) throws Exception {
		
		URL url = null;
		HttpURLConnection connection = null;
		url = new URL(urlString);
		// 新建连接实例
		connection = (HttpURLConnection) url.openConnection();
		// 设置连接超时时间，单位毫秒
		connection.setConnectTimeout(1000000);
		// 设置读取数据超时时间，单位毫秒
		connection.setReadTimeout(1000000);
		// 是否打开输出流 true|false
		connection.setDoOutput(true);
		// 是否打开输入流true|false
		connection.setDoInput(true);
		// 提交方法POST|GET
		connection.setRequestMethod("GET");
		// 是否缓存true|false
		connection.setUseCaches(false);
		// 设置Header
		 
		// 打开连接端口
		connection.connect();
		// 打开输出流往对端服务器写数据
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		
		// 刷新
		out.flush();
		// 关闭输出流
		out.close();
		// 往对端写完数据对端服务器返回数据 ,以BufferedReader流来读取
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
		// 返回结果
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		if (connection != null) {
			// 关闭连接
			connection.disconnect();
		}
		return JSONObject.fromObject(buffer.toString());
		
	}
	
}
