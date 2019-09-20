package com.manage.httpclient.cochain;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

public class OKHttp3WalletUtil {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	static public String asset = "/wallet/assets/";
	static public String tran = "/wallet/tran";
	static public String tranList = "/wallet/list/";
	static public String tokenCreate = "/wallet/token/create";
	static public String tokenDestroy = "/wallet/token/destroy";
	static public String host = "http://39.104.135.20:8011/v1";
	static public String createUser = "/wallet/add";
	OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(120, TimeUnit.SECONDS)
			.readTimeout(120, TimeUnit.SECONDS)
			.writeTimeout(120, TimeUnit.SECONDS)
			.build();
	
	Logger logger = Logger.getLogger(OKHttp3WalletUtil.class);

	/**
	 * 获取token数据
	 * @param uid
	 * @return
	 * @throws Exception
	 */
	public JSONArray getAssets(String uid) throws Exception {

		Request request = new Request.Builder().url(host + asset + uid).get().build();
		try (Response response = client.newCall(request).execute()) {
			String resp = response.body().string();
			
			return JSONArray.fromObject(resp);
		}
	}

	/**
	 * 转移token
	 * @param tokenid
	 * @param fromid
	 * @param toid
	 * @param amount
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public JSONObject tran(String tokenid, String fromid, String toid, String amount, String password) throws Exception {
		String json = tranParamters(tokenid,fromid,toid,amount,password);
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(host + tran).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return JSONObject.fromObject(response.body().string());
		}
	}

	String tranParamters(String tokenid, String fromid, String toid, String amount, String password) {

		Map<String, String> content = new HashMap<String, String>();
		content.put("tokenid", tokenid);
		content.put("fromId", fromid);
		content.put("toId", toid);
		content.put("amount", amount);
		content.put("password", password);
		return JSONObject.fromObject(content).toString();
	}

	public JSONArray getTranList( String begin,String end,String uid) throws Exception {
		String json = tranListParamters(begin,end);
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(host + tranList+uid).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return JSONArray.fromObject(response.body().string());
		}
	}

	String tranListParamters(String begin,String end) {
		Map<String, String> content = new HashMap<String, String>();
		content.put("end", end);
		content.put("begin", begin);
		return JSONObject.fromObject(content).toString();
	}

	/**
	 * 创建token 在确认账款节点添加
	 * @param user
	 * @param password
	 * @param amount
	 * @param endTime
	 * @return
	 * @throws Exception
	 */
	public String createToken(String user, String password, String amount, String endTime) throws Exception {
		RequestBody body = RequestBody.create(JSON, createTokenParamters(user,password,amount,endTime));
		Request request = new Request.Builder().url(host + tokenCreate).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	 String createTokenParamters(String user, String password, String amount, String endTime) {

		Map<String, String> content = new HashMap<String, String>();
		content.put("user", user);
		content.put("password", password);
		content.put("amount", amount);
		content.put("endTime", endTime);

		return JSONObject.fromObject(content).toString();
	}

	 /**
	 * 銷毀token
	 * @param user
	 * @param password
	 * @param amount
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public String destroyToken(String user, String password, String amount, String token) throws Exception {
		RequestBody body = RequestBody.create(JSON, destroyTokenParamters(user,password,amount,token));
		Request request = new Request.Builder().url(host + tokenDestroy).post(body).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	String destroyTokenParamters(String user, String password, String amount, String token) {

		Map<String, String> content = new HashMap<String, String>();
		content.put("user", user);
		content.put("password", password);
		content.put("amount", amount);
		content.put("token", token);

		return JSONObject.fromObject(content).toString();
	}
	
	/**
	 * 创建用户
	 * @param uid
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public String createUser(String uid,String password) throws IOException {
		logger.info(host + createUser+"/"+uid+"/"+password);
		String newUid = System.currentTimeMillis() + uid;
		Request request = new Request.Builder().url(host + createUser+"/"+newUid+"/"+password).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}
	
	/**
	 * 查询用户余额
	 * @param addr 用户地址
	 * @param token 64位16进制串
	 * @return
	 * @throws IOException
	 */
	public String getUserTokenValue(String addr,String token) throws IOException {
		logger.info(host +"/wallet/"+addr+"/"+token+"/balance");
		Request request = new Request.Builder().url(host +"/wallet/"+addr+"/"+token+"/balance").build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public static void main(String[] args) throws Exception {
		OKHttp3WalletUtil a = new OKHttp3WalletUtil();
//		System.out.println(a.createUser("1235","admin"));
		System.out.println(a.createToken("36", "E10ADC3949BA59ABBE56E057F20F883E", "100", "2020-10-11 11:23:33"));
//		System.out.println(a.getAssets("22"));
//		System.out.println(a.tran("20", "22", "21", "20", "admin"));
//		System.out.println(a.getTranList("","", "21"));
	}

}
