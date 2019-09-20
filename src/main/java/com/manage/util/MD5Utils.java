package com.manage.util;


import java.security.MessageDigest;

public class MD5Utils {

	/**
	 * 生成32位MD5码
	 */
	public static String encrypt(String password) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = password.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}

	/**
	 * SHA 加密
	 *
	 * @param data
	 *            需要加密的字符串
	 * @return 加密之后的字符串
	 * @throws Exception
	 */
	public static String encryptSHA(String data) throws Exception {
		// 验证传入的字符串
		if (data.isEmpty()) {
			throw new Exception("Input String is Null");
		}
		// 创建具有指定算法名称的信息摘要
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		// 使用指定的字节数组对摘要进行最后更新
		sha.update(data.getBytes());
		// 完成摘要计算
		byte[] bytes = sha.digest();
		// 将得到的字节数组变成字符串返回
		return bytesToHexString(bytes);
	}

	/*
	 * Convert byte[] to hex
	 * string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串。
	 *
	 * @param src byte[] data
	 *
	 * @return hex string
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return "";
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xff;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}


	public static void main(String args[]) throws Exception {
		System.out.println(MD5Utils.encrypt("gujiao"));
		System.out.println(MD5Utils.encryptSHA("3C6371885785CBF25A26BAFA82DF001F").toLowerCase());
		System.out.println(MD5Utils.encrypt("gujiao").toLowerCase());
		System.out.println(MD5Utils.encryptSHA("3c6371885785cbf25a26bafa82df001f").toLowerCase());
		System.out.println(MD5Utils.encrypt("gujiao").toLowerCase());
		System.out.println(MD5Utils.encryptSHA("402c5a6fe4c04c7d269c4532baecc8ee").toLowerCase());
	}
}
