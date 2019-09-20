package com.manage.util;

import java.io.*;

public class GetContentUtil {

	final static int SIZE = 4096;

	public String getFileStringByBufferReader(String path) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));

			String buf;
			StringBuffer str = new StringBuffer();
			boolean isNote = false;
			while ((buf = reader.readLine()) != null) {

				if (buf.isEmpty()) {
					continue;
				}
				buf = buf.replaceAll("\\s+(.*)", "$1"); // 去掉前面的空格
				if (buf.indexOf("/*") >= 0) {
					isNote = true;
					continue;
				}
				if (buf.lastIndexOf("*/") > 0) {
					isNote = false;
					continue;
				}
				if (isNote) {
					continue;
				}

				str.append(buf);

			}
			reader.close();
			return str.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";
	}

	public String getFileString(String path) {
		// 为了确保文件一定在之前是存在的，将字符串路径封装成File对象
		File file = new File(path);
		if (!file.exists()) {
			throw new RuntimeException("要读取的文件不存在");
		}

		// 创建文件字节读取流对象时，必须明确与之关联的数据源。
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 调用读取流对象的读取方法
		// 1.read()返回的是读取到的字节
		// 2.read(byte[] b)返回的是读取到的字节个数

		// 1.
		// int by=0;
		// while((by=fis.read())!=-1){
		// System.out.println(by);
		// }

		// 2.
		// byte[] buf = new byte[3];
		// int len = fis.read(buf);//len记录的是往字节数组里存储的字节个数
		// System.out.println(len+"...."+Arrays.toString(buf));//只是转成了字符串的表现形式
		// System.out.println(len+"...."+new String(buf,0,len));//转成字符串
		//
		// int len1 = fis.read(buf);
		// System.out.println(len1+"...."+new String(buf,0,len1));

		// 创建一个字节数组，定义len记录长度
		StringBuffer a = new StringBuffer();
		int len = 0;
		byte[] buf = new byte[SIZE];
		try {
			while ((len = fis.read(buf)) != -1) {
				a.append(new String(buf, 0, len).trim());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 关资源
		try {
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a.toString();
	}

	public static void main(String args[]) throws IOException {
		// GetContentUtil a = new GetContentUtil();
		//
		// System.out.println(a.getFileString(""));

		// String trayid =
		// "https://apidev.cochain.cn:8080/cochainapi/v1/artifacts/6a02192e15d27570a1948b68cd04d55794bee96408f119b8eee85d44ee0d10d7";
		// int length = AppConstants.getValue("prefixCode").length();
		// System.out.println(trayid.substring(length));
		String a = "/*{}*/";
		System.out.println(a.lastIndexOf("*/"));
		System.out.println(
				new GetContentUtil().getFileStringByBufferReader("C:\\Users\\Lenovo\\Desktop\\artifacts.json"));
	}
}
