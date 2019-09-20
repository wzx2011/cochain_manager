package com.manage.httpclient.cochain;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.manage.framework.AppConstants;

import net.sf.json.JSONObject;

public class HttpArtifactId {
	static Logger logger = Logger.getLogger(HttpArtifactId.class);

	public static JSONObject getArtifactId(String urlString, String contentString) throws Exception {
		
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("x-user-uid", AppConstants.getValue("uid"));
		String result = new BaseAndTraceOkhtt3Util().request(urlString, "post", contentString, headers);

		if (null != result) {
			return JSONObject.fromObject(result);
		}

		return JSONObject.fromObject("");


	}

	public static JSONObject getArtifactId(String urlString, String contentString, String userId) throws Exception {
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("x-user-uid", userId);
		String result = new BaseAndTraceOkhtt3Util().request(urlString, "post", contentString, headers);

		if (null != result) {
			return JSONObject.fromObject(result);
		}

		return JSONObject.fromObject("");

	}

}
