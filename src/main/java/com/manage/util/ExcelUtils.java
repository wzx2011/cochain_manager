package com.manage.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtils {

	public static void main(String[] args) {
		Map<String, Object> paraMa = new HashMap<String, Object>();
		paraMa.put("path", "c:/Wine.xlsx");
		List<Map<String, Object>> list = ExcelUtils.readWineXlsx(paraMa);
		System.out.println(list.toString());
	}

	/**
	 * 读取红酒信息
	 */
	public static List<Map<String, Object>> readWineXlsx(
			Map<String, Object> paraMap) {
		// 文件路徑
		String path = paraMap.get("file_path").toString();
		// 文件類型
		String fileType = path.substring(path.lastIndexOf("."));
		// 用戶ID
		String create_user = paraMap.get("create_user").toString();
		// 酒莊ID
		String chateau_id = paraMap.get("chateau_id").toString();

		List<Map<String, Object>> listMap = null;
		try {
			InputStream stream = new FileInputStream(path);
			Workbook wb = null;
			if (fileType.equals(".xls")) {
				wb = new HSSFWorkbook(stream);
			} else if (fileType.equals(".xlsx")) {
				wb = new XSSFWorkbook(stream);
			}

			listMap = new ArrayList<Map<String, Object>>();
			Sheet sheet = wb.getSheetAt(0);
			// 行数
			int rowSize = sheet.getLastRowNum() + 1;
			for (int i = 0; i < rowSize; i++) {
				Row row = sheet.getRow(i);
				if (i != 0) {
					// 略过空行
					if (row == null) {
						continue;
					}
					// 数据行对象
					Map<String, Object> map = new HashMap<String, Object>();

					Cell CellAppellation = row.getCell(0);
					Cell CellVintage = row.getCell(1);
					Cell CellRegion = row.getCell(2);
					Cell CellCategory = row.getCell(3);
					Cell CellAlcoholVolum = row.getCell(4);
					Cell CellVolum = row.getCell(5);
					Cell CellPackingRatio = row.getCell(6);
					Cell CellGradeRank = row.getCell(7);
					Cell CellDriedSweet = row.getCell(8);
					Cell CellBrand = row.getCell(9);
					Cell CellWineLabel = row.getCell(10);
					Cell CellAvailable = row.getCell(11);

					String Appellation = getCellValue(CellAppellation);
					String Vintage = getCellValue(CellVintage);
					String Region = getCellValue(CellRegion);
					String Category = getCellValue(CellCategory);
					String AlcoholVolum = getCellValue(CellAlcoholVolum);
					String Volum = getCellValue(CellVolum);
					String PackingRatio = getCellValue(CellPackingRatio);
					String GradeRank = getCellValue(CellGradeRank);
					String DriedSweet = getCellValue(CellDriedSweet);
					String Brand = getCellValue(CellBrand);
					String WineLabel = getCellValue(CellWineLabel);
					String Available = getCellValue(CellAvailable);

					// 过滤空行
					if (Appellation != null && !Appellation.equals("")) {

						map.put("chateau_id", chateau_id);
						map.put("wine_name_en", Appellation);
						map.put("wine_vintage", Vintage);
						map.put("wine_region_en", Region);
						map.put("wine_varietal_en", Category);
						map.put("wine_alcohol", AlcoholVolum);
						map.put("wine_volum", Volum);
						map.put("wine_packingratio", PackingRatio);
						map.put("wine_grade", GradeRank);
						// 干甜
						map.put("wine_driedsweet_en", DriedSweet);
						map.put("wine_brand_id", Brand);
						map.put("wine_label_id", WineLabel);
						// 可售状态
						map.put("available", Available);
						map.put("create_user", create_user);

						// 保存至集合
						listMap.add(map);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			listMap = null;
		}
		return listMap;
	}

	/**
	 * 标题列样式
	 */
	public static CellStyle getHeaderCellStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		// 水平居左
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// 垂直居下
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_BOTTOM);
		// 设置边框实线
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		// 不自动换行
		style.setWrapText(false);
		// 文字样式
		Font font = wb.createFont();
		// 设置字体为斜体字
		font.setItalic(false);
		// 将字体设置为默认
		font.setColor(Font.COLOR_NORMAL);
		// 将字体大小设置为18px
		font.setFontHeightInPoints((short) 14);
		// 将"CorpoS"字体应用到当前单元格上
		font.setFontName("CorpoS");
		// 保存字体样式
		style.setFont(font);

		return style;
	}

	/**
	 * 内容列样式 居左
	 */
	public static CellStyle getLeftCellStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		// 水平居左
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// 垂直居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置边框虚线
		style.setBorderBottom(HSSFCellStyle.BORDER_DASHED);
		style.setBorderLeft(HSSFCellStyle.BORDER_DASHED);
		style.setBorderRight(HSSFCellStyle.BORDER_DASHED);
		style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
		// 自动换行
		style.setWrapText(true);
		// 文字样式
		Font font = wb.createFont();
		// 设置字体为斜体字
		font.setItalic(false);
		// 将字体设置为默认
		font.setColor(Font.COLOR_NORMAL);
		// 将字体大小设置为18px
		font.setFontHeightInPoints((short) 11);
		// 将"CorpoS"字体应用到当前单元格上
		font.setFontName("CorpoS");
		// 保存字体样式
		style.setFont(font);

		return style;
	}

	/**
	 * 内容列样式 居中
	 */
	public static CellStyle getCenterCellStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		// 水平居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 垂直居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 设置边框虚线
		style.setBorderBottom(HSSFCellStyle.BORDER_DASHED);
		style.setBorderLeft(HSSFCellStyle.BORDER_DASHED);
		style.setBorderRight(HSSFCellStyle.BORDER_DASHED);
		style.setBorderTop(HSSFCellStyle.BORDER_DASHED);
		// 自动换行
		style.setWrapText(true);
		// 文字样式
		Font font = wb.createFont();
		// 设置字体为斜体字
		font.setItalic(false);
		// 将字体设置为默认
		font.setColor(Font.COLOR_NORMAL);
		// 将字体大小设置为18px
		font.setFontHeightInPoints((short) 11);
		// 将"CorpoS"字体应用到当前单元格上
		font.setFontName("CorpoS");
		// 保存字体样式
		style.setFont(font);

		return style;
	}

	/**
	 * 获取列值
	 */
	public static String getCellValue(Cell cell) {
		String value = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数字
			value = String.valueOf((int) cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_STRING: // 字符串
			value = String.valueOf(cell.getStringCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			value = String.valueOf(cell.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_BLANK: // 空值
			break;
		case HSSFCell.CELL_TYPE_ERROR: // 故障
			break;
		default:
			break;
		}
		return value;
	}

	/**
	 * 获取日期列值
	 */
	public static String getDateCellValue(Cell cell) {
		Date date = cell.getDateCellValue();
		DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		return formater.format(date);
	}

}
