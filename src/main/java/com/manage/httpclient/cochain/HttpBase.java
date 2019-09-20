package com.manage.httpclient.cochain;

import net.sf.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

public class HttpBase {

	public static JSONObject setArtifactRelation(String urlString, String contentString) throws Exception {

		String result = new BaseAndTraceOkhtt3Util().request(urlString, "post", contentString, null);

		if (null != result) {
			return JSONObject.fromObject(result);
		}

		return JSONObject.fromObject("");
	}

	public static JSONObject setArtifactRelation(String urlString, String contentString, Map<String, Object> userMap)
			throws Exception {

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("x-older-token", userMap.get("token").toString());
		headers.put("x-user-uid", userMap.get("user_addr").toString());
		String result = new BaseAndTraceOkhtt3Util().request(urlString, "post", contentString, headers);

		if (null != result) {
			return JSONObject.fromObject(result);
		}

		return JSONObject.fromObject("");
	}
}
