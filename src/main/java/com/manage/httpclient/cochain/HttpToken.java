package com.manage.httpclient.cochain;

import com.manage.framework.AppConstants;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpToken { 

	/**
	 * @param urlString
	 * @param contentString
	 * @return
	 */
	static Logger logger = Logger.getLogger(HttpToken.class);
	
	 
	
	public static String getToken(String userAddr,String password) throws Exception {
		String url = AppConstants.getValue("tokenUrl");
		if (userAddr.equals("") || password.equals("")) {
			url =url.replace("user_addr", "d01d365d9252521a4aeffe8e921d23376b46aded");
			url = url.replace("password", "admin");
		} else {
			url =url.replace("user_addr", userAddr);
			url = url.replace("password", password);
		}
		
		 
		String result = new BaseAndTraceOkhtt3Util().request(url, "get", "", null);

		if (null != result) {
			return JSONObject.fromObject(result).getString("token");
		}

		return "";

	}
	
	public static void main(String args[]) throws Exception {
		System.out.println(HttpToken.getToken("", ""));
	}
}
