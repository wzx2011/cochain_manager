package com.manage.httpclient.cochain;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BaseAndTraceOkhtt3Util {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(120, TimeUnit.SECONDS)
			.readTimeout(120, TimeUnit.SECONDS).writeTimeout(120, TimeUnit.SECONDS).build();

	Logger logger = Logger.getLogger(BaseAndTraceOkhtt3Util.class);

	public String request(String url, String type, String content, Map<String, Object> headers) throws IOException {
		Builder bulider = getBuilder(url, headers);
		if (type.equalsIgnoreCase("POST")) {
			return this.post(bulider, content);
		} else {
			return this.get(bulider, content);
		}

	}

	private Builder getBuilder(String url, Map<String, Object> headers) {
		
		Builder bulider = new Request.Builder().url(url);
		if(null==headers) {
			return bulider;
		}
		if (headers.containsKey("x-user-uid")) {
			bulider.addHeader("x-user-uid", headers.get("x-user-uid").toString());
		}
		if (headers.containsKey("x-older-token")) {
			bulider.addHeader("x-older-token", headers.get("x-older-token").toString());
		}
		return bulider;
	}

	public String get(Builder bulider, String urlString) throws IOException {
		Request request = bulider.get().build();
		try (Response response = client.newCall(request).execute()) {
			String resp = response.body().string();

			return resp;
		}
	}

	public String post(Builder bulider, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = bulider.post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	public static void main(String args[]) throws IOException {
		
	String result =	new BaseAndTraceOkhtt3Util().request("http://39.104.135.20/cochainapi/user/v2/token/0x48a041afeb8ede5753cfa90ecf15723521bc27ab/123456", "get", "", null);
	System.out.println(result);
	}

}
