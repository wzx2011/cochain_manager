package com.manage.util.db;

import com.manage.util.Const;
import com.manage.util.ImageUtils;

import java.io.File;
import java.sql.SQLException;

public class ImportDB {

	public static void updateItemById(String item, String images) {
		// SQL语句
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE item ");
		sb.append("SET ");
		sb.append("image = '").append(images).append("' ");
		sb.append("WHERE item = '").append(item).append("' ");
		// 创建DBHelper对象
		DBHelper db = new DBHelper(sb.toString());
		try {
			// 执行语句，得到结果集
			int result = db.pst.executeUpdate();
			System.out.println("result : " + result);
			db.close();// 关闭连接
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String images = ImageUtils.encodeImgageToBase64(new File(
				"C://ceshi.jpg"));
		updateItemById("IT000001", Const.image_prefix + images);
	}
}
