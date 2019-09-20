package com.manage.httpclient.cochain;

import com.manage.framework.AppConstants;

import net.sf.json.JSONObject;

public class HttpUser {
	public static JSONObject getBlockchainAccount(String password) throws Exception {
		String url = AppConstants.getValue("blockchainAccount").replaceAll("password", password);
		String result = new BaseAndTraceOkhtt3Util().request(url, "post", "", null);

		if (null != result) {
			return JSONObject.fromObject(result);
		}

		return JSONObject.fromObject("");
	}
}
