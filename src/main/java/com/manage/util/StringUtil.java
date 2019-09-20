package com.manage.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @Description 对java中常见的字符串使用的功能进行二次封装
 * @Date 2018/10/9 15:33
 **/
public class StringUtil {
    private static final Logger log= LoggerFactory.getLogger(StringUtil.class);

	/** 8 位 UCS 转换格式 */
	public static final String UTF_8 = "UTF-8";
	
	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	public static final String ISO_8859_1 = "ISO-8859-1";

	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	public static final String US_ASCII = "US-ASCII";

	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	public static final String UTF_16BE = "UTF-16BE";

	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	private static final String UTF_16LE = "UTF-16LE";

	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	public static final String UTF_16 = "UTF-16";

	/** 中文超大字符集 */
	public static final String GBK = "GBK";

	/** 中文超大字符集 */
	public static final String GB2312 = "GB2312";
    /**
     * 判别字符串是否为null或者没有内容
     * @param inStr 被判断的输入参数
     * @return  布尔值：true=表示输入字符串为null或者没有内容
     *                  false=表示输入字符串不为null或者有内容
     */
    public static boolean zero(String inStr) {
        return ((null == inStr) || (inStr.length() <= 0));
    }
    /**
     * 判断字符串是否为null或没有内容或全部为空格。
     * @param inStr 被判断的输入参数
     * @return  布尔值：true=表示输入字符串为null或没有内容或全部为空格
     *                  false=表示输入字符串不为null或有内容或全部不为空格
     */
    public static boolean empty(String inStr) {
        return (zero(inStr) || (inStr.trim().equals("")));
    }
    
    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }
    
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 去掉输入字符串首尾空格
     * @param inStr 输入字符串
     * @return 首尾无空格的字符串
     */
    public static String trim(String inStr) {
        if (empty(inStr)) {
            return inStr;
        }
        return inStr.trim();
    }

    /**
     * 判断字符串是否内容相同，不区分大小写
     * @param s1  第1个输入字符串
     * @param s2  第2个输入字符串
     * @return 布尔值=true：两个字符串相等
     *                =false:两个字符串不相等
     */
    public static boolean equalsIgnoreCase(String s1, String s2) {
        if (null == s1) {
            return false;
        }
        return s1.equalsIgnoreCase(s2);
    }

    /**
     * 把字符数组转换成字符串
     * @param array 字符数组
     * @return 转换后的字符串
     */
    public static String toString(char[] array) {
        return String.valueOf(array);
    }

  
    /**
     * 在字符串中，用新的字符串替换指定的字符
     * @param src 需要替换的字符串
     * @param charOld 被替换的字符
     * @param strNew  用于替换的字符串
     * @return 已经替换好的字符串
     */
    public static String replace(String src, char charOld, String strNew) {
        if (null == src) {
            return src;
        }
        if (null == strNew) {
            return src;
        }

        StringBuilder buf = new StringBuilder();
        for (int i = 0, n = src.length(); i < n; i++) {
            char c = src.charAt(i);
            if (c == charOld) {
                buf.append(strNew);
            } else {
                buf.append(c);
            }
        }
        return buf.toString();
    }

    /**
     * 在字符对象中，用新的字符串替换指定的字符串
     * @param src 需要替换的字符对象
     * @param strOld 被替换的字符串
     * @param strNew  用于替换的字符串 
     */
    public static void replace(StringBuilder src, String strOld, String strNew) {
        if ((null == src) || (src.length() <= 0)) {
            return;
        }
        String s = replace(src.toString(), strOld, strNew);
        src.setLength(0);
        src.append(s);
    }

    /**
     * 在字符串中，用新的字符串替换指定的字符串
     * @param src 需要替换的字符对象
     * @param strOld 被替换的字符串
     * @param strNew  用于替换的字符串
     * @return 已经被替换的字符串
     */
    public static String replace(String src, String strOld, String strNew) {
        if (null == src) {
            return src;
        }
        if (zero(strOld)) {
            return src;
        }
        if (null == strNew) {
            return src;
        }
        if (equals(strOld, strNew)) {
            return src;
        }
        
        return src.replaceAll(strOld, strNew);
    }

    /**
     * 把字符串的第一个字符变为大写
     * @param s 输入字符串
     * @return 返回第一个字符是大写的字符串
     */
    public static String upperFirst(String s) {
        String str = null;
        if (null != s) {
            if (s.length() == 1) {
                str = s.toUpperCase();
            } else {
                str = s.substring(0, 1).toUpperCase() + s.substring(1);
            }
        }
        return str;
    }

    /**
     * 把字符对象第一个字符变为大写
     * @param sb 字符对象
     */
    public static void upperFirst(StringBuilder sb) {
        if ((null != sb) && (sb.length() > 0)) {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
    }

    /**
     * 把字符串的第一个字符变为小写
     * @param s 输入的字符串
     * @return 返回的第一个字符是小写的字符串
     */
    public static String lowerFirst(String s) {
        String str = null;
        if (null != s) {
            if (s.length() == 1) {
                str = s.toLowerCase();
            } else {
                str = s.substring(0, 1).toLowerCase() + s.substring(1);
            }
        }
        return str;
    }

    /**
     * 把字符对象的第一个字符变为小写
     * @param sb 输入的字符对象
     */
    public static void lowerFirst(StringBuilder sb) {
        if ((null != sb) && (sb.length() > 0)) {
            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        }
    }

    /**
     * 根据指定的delima标志获取输入字符串的后缀
     * @param str 输入字符串
     * @param delima 指定的标志,一般是一个字符，如“.”
     * @return 输入字符串子的后缀
     */
    public static String getLastSuffix(String str, String delima) {
        if (zero(delima)) {
            return str;
        }

        String suffix = "";
        if (!zero(str)) {
            int index = str.lastIndexOf(delima);
            if (index >= 0) {
                suffix = str.substring(index + delima.length());
            } else {
                suffix = str;
            }
        }
        return suffix;
    }

    /**
     * 根据指定的delima标志获取输入字符串的前缀
     * @param src 输入字符串
     * @param delima 指定的标志,一般是一个字符，如“.”
     * @return 输入字符串的前缀
     */
    public static String getLastPrefix(String src, String delima) {
        if (zero(delima)) {
            return src;
        }

        String prefix = "";
        if (!zero(src)) {
            int index = src.lastIndexOf(delima);
            if (index >= 0) {
                prefix = src.substring(0, index);
            }
        }
        return prefix;
    }

    /**
     * 将str字符串按照其中出现的delim分割成字符串数组
     * @param str 输入的字符串
     * @param delim 分割标志
     * @return 分割好的数组
     */
    public static String[] split(String str, String delim) {
        if (zero(str) || zero(delim)) {
            return new String[0];
        }
        return str.split(delim);
    }

    /**
     * 将str字符串按照其中出现的delim分割成字符串数组,并能去掉前后空格
     * @param str 输入的字符串
     * @param delim 分割标志
     * @param trim =true 去掉前后空格 =false 不去掉前后空格
     * @return 分割好的数组
     */
    public static String[] split(String str, String delim, boolean trim) {
        String[] set = split(str, delim);
        if (trim) {
            for (int i = 0; i < set.length; i++) {
                set[i] = set[i].trim();
            }
        }
        return set;
    }

    /**
     * 判断输入字符串是否包含指定的字符串
     * @param str 输入字符串
     * @param searchStr 指定是否包含的字符串
     * @return  =true:包含指定的字符串
     *           =false:不包含指定的字符串
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        if (searchStr.length() == 0) // ""空串不认为被包含。String.indexOf()认为空串被包含
        {
            return false;
        } else {
            return str.indexOf(searchStr) >= 0;
        }
    }

    /**
     * 将set字符串数组从fromIndex开始以后的元素合并成以delim为分割符的字符串
     * @param set
     * @param delim
     * @param fromIndex 以0开始
     * @return
     */
    public static String join( String[] set, String delim, int fromIndex )
    {
        if ( ( null == set ) || ( set.length <= 0 ) || ( fromIndex >= set.length ) ) {
            return "";
        }
        if ( fromIndex < 0 ) {
            fromIndex = 0;
        }
        StringBuffer sb = new StringBuffer();
        sb.append( set[fromIndex] );
        for( int i = fromIndex+1; i < set.length; i++ ) {
            sb.append( delim );
            sb.append( set[i] );
        }
        return sb.toString();
    }
    
    
    /**
     * 把占位符号进行替换
     *@param replaceContentSrc:被替换的原字符串
     *@param inputPrifx：input输入框的名称前缀
     *@return 替换好的字符串
     */
    public static StringBuilder replaceSpecialChar(String replaceContentSrc,
            String inputPrifx) {
        String oldReplaceContent = replaceContentSrc;
        StringBuilder builder = new StringBuilder();
        if(StringUtil.empty(oldReplaceContent)){
            return builder;    
        }
        String splitChar = new String("_");
        String replaceStrBegin = "<input type=\"text\" class=\"inputUnderLine2\" name=\"" + inputPrifx;
        String replaceStrMiddle="\" id=\""+inputPrifx+"Id";
        String replaceStrend = "\">&nbsp;&nbsp;&nbsp;";
        // 首先判断开始有没有
        String beginChar = oldReplaceContent.substring(0, splitChar.length());
        if(StringUtil.equals(beginChar, splitChar)) {
            builder.append(replaceStrBegin + 0 +replaceStrMiddle+0+ replaceStrend);
            oldReplaceContent = oldReplaceContent.substring(splitChar.length());
        }
        // 把中间的替换掉
        boolean flagReplace = false;
        String endChar = oldReplaceContent.substring(
                oldReplaceContent.length() - splitChar.length(),
                oldReplaceContent.length());
        if(StringUtil.equals(endChar, splitChar)) {
            oldReplaceContent = oldReplaceContent.substring(0,
                    oldReplaceContent.length() - splitChar.length());
            flagReplace = true;
        }
        // 把中间的去掉
        String[] splitStrs = StringUtil.split(oldReplaceContent, splitChar);
        if(flagReplace) {
            for(int i = 0; i < splitStrs.length; i++) {
                String q = splitStrs[i];
                builder.append(q);
                builder.append(replaceStrBegin + (i + 1) +replaceStrMiddle+(i+1)+ replaceStrend);

            }
        } else {
            for(int i = 0; i < splitStrs.length; i++) {
                String q = splitStrs[i];
                builder.append(q);
                if(i != splitStrs.length - 1) {
                    builder.append(replaceStrBegin + (i + 1) +replaceStrMiddle+(i+1)+ replaceStrend);
                }
            }
        }
        return builder;
    }
    
    
    /**
     * 指定字符串出现的次数.<br>
    *@param srcStr：查找的字符串
    *@param countStr：指定要查找的字符串
    *@return
     */
    public static int countStringNumber(String srcStr,String countStr){
        int indexCount = 0;
        int index = 0;
        int count=0;
        for(;;) {
            index = srcStr.indexOf(countStr, indexCount);
            if(index == -1){
                break;
            }
            count++;
            indexCount = (index += countStr.length());
        }
        return count;
    } 
    
    /**
	 * 比较第二个字符串数组是否被第一个字符串数组包含
	* @param arr1 原字符串数组
	* @param arr2 被追加的字符串数组
	* @return 第二个字符串追加到第一个字符串后的String
	 */
	public static String compareAddDiffArr(String[] arr1,String[] arr2){
		String arr2Str = "";
		for(int i=0;i<arr2.length;i++){
			if(i == 0){
				arr2Str = arr2[i];
			}else{
				arr2Str += (","+arr2[i]);
			}
		}
		arr2Str += ",";
		for(int j=0;j<arr1.length;j++) {
            arr2Str = arr2Str.replace(arr1[j] + ",", "");
        }
		arr2Str = (arr2Str.endsWith(",")) ? arr2Str.substring(0,arr2Str.length()-1) : arr2Str;
		return arr2Str;
	}
	/**
	 * 比较第二个字符串是否被第一个字符串包含，分割字符串用","
	* @author 作者的名字
	* @version 创建时间：Sep 10, 2010  10:31:55 AM
	* @param arr1 原字符串
	* @param arr2 被追加的字符串
	* @return 第二个字符串追加到第一个字符串后的String
	 */
	public static String addStrDiffStr(String arr1,String arr2) {
		arr1 = arr1.replaceAll(" ", "");
		arr2 = arr2.replaceAll(" ", "");
		String[] arr1s = {};
		String[] arr2s = {};
		String str = "";
		if(arr1 != null && !arr1.equals("")) {
            arr1s = arr1.split(",");
        }else{
			arr1 = "";
		}
		if(arr2 != null && !arr2.equals("")) {
            arr2s = arr2.split(",");
        }else{
			arr2 = "";
		}
		String temp[]=new String[arr1s.length+arr2s.length];
        //将数组arr1s的元素复制到temp中
		System.arraycopy(arr1s,0,temp,0,arr1s.length);
        //将数组arr2s的元素复制到temp中
		System.arraycopy(arr2s,0,temp,arr1s.length,arr2s.length);
		
		//连接数组完成,开始清除重复元素
		for(int i=0;i<temp.length;i++){
			if(temp[i]!="-1"){
				for(int j=i+1;j<temp.length;j++){
					if(temp[i].equals(temp[j])){
                        //将发生重复的元素赋值为"-1"
						temp[j]="-1";
					}	
				}
			}
		}
		StringBuffer stringBuffer=new StringBuffer();
		for(int i=0,j=0;j<temp.length && i<temp.length;i++,j++){
			if(temp[i].equals("-1")){
				j--;
		    }
		    else{
                stringBuffer.append(temp[i]);
                stringBuffer.append(", ");
		    }
		}
        str=stringBuffer.toString();
		if(!str.equals("")){
			str = str.replace("-1, ","");
			str = str.replace(", -1",""); 
			str = (str.endsWith(", ")) ? str.substring(0,str.length()-2) : str;
		}
		return str;
	}
	
	/**
	 * 
	 * @Description:  判断第一个字符串是否为第二个字符串开始，并且去掉
	 * @return 设定文件
	 * @return String 返回类型
	 */
	public static String subStrStartDiffStr(String str,String ch){
		str = str.trim();
		ch = ch.trim();
		return (str.startsWith(ch)) ? str.substring(ch.length(), str.length()) : str;
	}
	
	/**
	 * 判断字符串的编码
	 * 
	 * @param str
	 * @return
	 */
	public static String getEncoding(String str) {
		String[] encodeStr={UTF_8,ISO_8859_1,GBK,GB2312,US_ASCII,UTF_16BE,UTF_16LE,UTF_16};
		String encode="";
		for (String string : encodeStr) {
			encode=checkEncoding(str, string);
			if(isNotBlank(encode)){
				return encode;
			}
		}
		return "";
	}  
	public static String checkEncoding(String str,String encode){
		try {
			if (str.equals(new String(str.getBytes(encode), encode))) {
				String s = encode;
				return s;
			}
		} catch (Exception exception) {
            log.info("异常信息：{}",exception);
		}
		return "";
	}
	/**
	 * 字母Z使用了两个标签，这里有27个值
	 * 
	 * i, u, v都不做声母, 跟随前面的字母
	 */
	private char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖',
			'昔', '压', '匝', '座' };

	private char[] alphatable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z' };

	/**
	 * 切割字符长度
	 * @param str 源字符串
	 * @param len 需要的长度
	 * @param gb 中文字占2位 
	 * @return List<String>
	 */
	public static List<String> subGbstring(String str,int len,boolean gb){
		List<String> list = new ArrayList<String>();;
		if(gb){
			int nowlen=0;
			int start=0;
			StringUtil obj = new StringUtil();
			for (int i = 0; i < str.length(); i++) {
				int strgb = obj.gbValue(str.charAt(i));
				if (strgb < obj.table[0]){//非中文简体
					nowlen++;
				}else{
					nowlen+=2;
				}
				if(nowlen==len){
					list.add(str.substring(start, i+1));
					start=i+1;
					nowlen=0;
				}else if(nowlen>len){
					list.add(str.substring(start, i));
					start=i;
					nowlen=0;
				}else if(i+1==str.length()){
					list.add(str.substring(start, i+1));
					start=i;
					nowlen=0;
				}
			}
		}else{
			int end=len;
			for (int start = 0; start < str.length();) {
				if(end+1>str.length()){
					end=str.length();
				}
				list.add(str.substring(start, end));
				start=end+1;
				end+=start;
			}
		}
		return list;
	}
	
	public int[] table = new int[27];
	{// 初始化
		for (int i = 0; i < 27; ++i) {
			table[i] = gbValue(chartable[i]);
		}
	}

	/**
	 * 主函数,输入字符得到他的声母,
	 * 
	 * 英文字母返回对应的大写字母
	 * 
	 * 其他非简体汉字返回 '0'
	 * 
	 * @param ch
	 * @return
	 */
	public char Char2Alpha(char ch) {
		if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 'a' + 'A');
        }
		if (ch >= 'A' && ch <= 'Z') {
            return ch;
        }
		int gb = gbValue(ch);
		if (gb < table[0]){
			return '0';
		}
		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb)) {
                break;
            }
		}
		if (i >= 26) {
            return '0';
        }else {
            return alphatable[i];
        }
	}

	/**
	 * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
	 * 
	 * @param SourceStr
	 * @return
	 */
	public String String2Alpha(String SourceStr) {
        StringBuffer Result=new StringBuffer();
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
                Result.append(Char2Alpha(SourceStr.charAt(i)));
			}
		} catch (Exception e) {
            Result=new StringBuffer();
		}
		return Result.toString();
	}

	private boolean match(int i, int gb) {
		if (gb < table[i]) {
            return false;
        }
		int j = i + 1;
		// 字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i])) {
            ++j;
        }
		if (j == 26) {
            return gb <= table[j];
        }else {
            return gb < table[j];
        }
	}

    /**
     * 取出汉字的编码
     * @param ch
     * @return
     */
	private int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2) {
				return 0;
			}
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * Md5加密
	 * 
	 * @param s
	 * @return
	 */
	public static String getMd5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * Md5加密
	 * 
	 * @param s
	 * @return
	 */
	public static String getMd5(String s, String charset) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] strTemp = s.getBytes(charset);
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到一个n位的随机数 第一位不能为0
	 * 
	 * @param n
	 *            位数
	 * @return
	 */
	public static String getRand(int n) {
		Random rnd = new Random();
		String pass = "0";
		int x = rnd.nextInt(9);
		/** 过滤第一位为0 */
		while (x == 0) {
			x = rnd.nextInt(9);
		}
		pass = String.valueOf(x);
		StringBuffer stringBuffer=new StringBuffer(pass);
		for (int i = 1; i < n; i++) {
            stringBuffer.append(String.valueOf(rnd.nextInt(9)));
		}
        pass=stringBuffer.toString();
		return pass;
	}

	/**
	 * java按要求长度截取字段
	 * 
	 * @param str
	 *            字符
	 * @param num
	 *            长度
	 * @return
	 */
	public static String getStrLen(String str, int num) {
		int forNum = 0;
		int alli = 0;
        // 要循环的长度
		int strLen = 0;
		if (num <= 0) {
			return str;
		}
		if (null == str) {
			return null;
		}
		if (str.length() >= num) {
			strLen = num;

		} else {
			strLen = str.length();
		}
		for (int i = 0; i < strLen; i++) {
			if (num == Math.floor(forNum / 2f)) {
                break;
            }
			if (str.substring(i, i + 1).getBytes().length > 1) {
				// 如果是字符
				alli = alli + 1;
			}
			alli = alli + 1;
			if (alli >= num) {
				return str.substring(0, i);
			}
		}
		return str.substring(0, strLen);
	}

	/**
	 * 判断字符是否超过长度
	 * 
	 * @param str
	 * @param num
	 * @return 超过规定字符返回true
	 */
	public static boolean isLen(String str, int num) {
		int forNum = 0;
		int alli = 0;
        // 要循环的长度
		int strLen = 0;
		if (str.length() >= num) {
			strLen = num;
            // 超过规定字符返回true
			return true;
		} else {
			strLen = str.length();
		}
		for (int i = 0; i < strLen; i++) {
			if (num == Math.floor(forNum / 2f)) {
                break;
            }
			if (str.substring(i, i + 1).getBytes().length > 1) {
				// 如果是字符
				alli = alli + 1;
			}
			alli = alli + 1;
		}
		if (alli > num) {
            // 超过规定字符返回true
			return true;
		}
        // 不超过规定字符返回False
		return false;
	}
	/**
	 * 填充左边字符
	 * 
	 * @param source
	 *            源字符串
	 * @param fillChar
	 *            填充字符
	 * @param len
	 *            填充到的长度
	 * @return 填充后的字符串
	 */
	public static String fillLeft(String source, char fillChar, int len) {
		StringBuffer ret = new StringBuffer();
		if (null == source){
			ret.append("");
        }else if (source.length() > len) {
			ret.append(source);
		} else {
			int slen = source.length();
			while (ret.toString().length() + slen < len) {
				ret.append(fillChar);
			}
			ret.append(source);
		}
		return ret.toString();
	}
	/**
	 * 填充右边字符
	 * 
	 * @param source
	 *            源字符串
	 * @param fillChar
	 *            填充字符
	 * @param len
	 *            填充到的长度
	 * @return 填充后的字符串
	 */
	public static String filRight(String source, char fillChar, int len) {
		StringBuffer ret = new StringBuffer();
		if (null == source) {
            ret.append("");
        }else if (source.length() > len) {
			ret.append(source);
		} else {
			ret.append(source);
			while (ret.toString().length() < len) {
				ret.append(fillChar);
			}
		}
		return ret.toString();
	}
	public static String filterStr(String str) {
		if (null == str || "".equals(str)) {
			return str;
		}
		str = str.replaceAll("'", "''");
		return str;
	}

	/**
	 * 检测字符是否是数字
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isDigit(char c) {
		String nums = "0123456789.";
		if (nums.indexOf(String.valueOf(c)) == -1) {
			return false;
		}
		return true;
	}
	public static boolean isNumeric(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)|([0-9]+)([.])?$");
	}
	public static String substring(String str, int num) {
		byte[] substr = new byte[num];
		System.arraycopy(str.getBytes(), 0, substr, 0, num);
		str = new String(substr);
		return str;
	}

	public static String checkStr(String inputStr) {
//		String error = "";
        StringBuffer error = new StringBuffer();
		if (null != inputStr && !"".equals(inputStr.trim())) {
			char c;
			for (int i = 0; i < inputStr.length(); i++) {
				c = inputStr.charAt(i);
				if (c == '"') {
//					error += " 特殊字符[\"]";
                    error.append(" 特殊字符[\"]");
				}
				if (c == '\'') {
//					error += " 特殊字符[']";
                    error.append(" 特殊字符[']");
				}
				if (c == '<') {
//					error += " 特殊字符[<]";
                    error.append(" 特殊字符[<]");
				}
				if (c == '>') {
//					error += " 特殊字符[>]";
                    error.append(" 特殊字符[>]");
				}
				if (c == '&') {
//					error += " 特殊字符[&]";
                    error.append(" 特殊字符[&]");
				}
				if (c == '%') {
//					error += " 特殊字符[%]";
                    error.append(" 特殊字符[%]");
				}
			}
		}
		return error.toString();
	}
	/**
	 * 检测字符是否为空,为空的时候返回提示
	 * @param str
	 * @param msg 为空的时候返回提示
	 * @return
	 */
	public static String isBlankToMsg(String str,String msg){
		String returnstr="";
		if (StringUtil.isBlank(str)) {
			returnstr=msg+",";
		}
		return returnstr;
	}
	
	public static String getFileName(String filepath) {
		if (StringUtil.isNotBlank(filepath)) {
			return filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length());
		}
		return "";
	}

	public static String changeCharset(String str, String oldCharset, String newCharset) {
		if (str != null) {
			// 用默认字符编码解码字符串。
			byte[] bs = null;
			try {
				if (StringUtil.isNotBlank(oldCharset)) {
					bs = str.getBytes(oldCharset);
				} else {
					bs = str.getBytes();
				}
			} catch (UnsupportedEncodingException e1) {
                log.info("异常信息：{}",e1);
			}
			// 用新的字符编码生成字符串
			try {
				String newstr = new String(bs, newCharset);
				return newstr;
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}

	public static String makeSign(String value){
		String str="";
		if(null==value){
			return str;
		}
		str=value.trim();//去掉前后空格
		str=str.replaceAll(">", "&gt;");
		str=str.replaceAll("<", "&lt;");
		str=str.replaceAll("&", "&amp;");
		str=str.replaceAll("\"", "&quot;");
		return str;
	}
	/**
	 * 截取超长的信息，多余用...
	 * @param str 备注
	 * @param len 长
	 * @return 截取后的信息
	 */
	public static String intercept(String str,int len){
		String newstr = "";
		if(null==str){
			return newstr;
		}
		if(str.length()>len){
			newstr = str.substring(0, len)+"...";
		}else{
			newstr = str;
		}
		return newstr;
	} 
	/**
	 * 转义页面输入的特殊符号
	 * @param str
	 * @return
	 */
	public static String replaceHtml(String str){
		if(null==str){
			return "";
		}
		str = StringUtil.replace(str, "&", "&amp;");
		str = StringUtil.replace(str, "'", "&apos;");
		str = StringUtil.replace(str, "\"", "&quot;");
		str = StringUtil.replace(str, "\n", "<br>");
        // 替换跳格
		str = StringUtil.replace(str, "\t", "&nbsp;&nbsp;");
        // 替换空格
		str = StringUtil.replace(str, " ", "&nbsp;");
		return str;
	}
	/**
	 * 反向转义页面输入的特殊符号
	 * @param str
	 * @return
	 */
	public static String reReplaceHtml(String str){
		if(null==str){
			return "";
		}
		str = StringUtil.replace(str, "&amp;","&");
		str = StringUtil.replace(str,"&apos;","'" );
		str = StringUtil.replace(str, "&quot;", "\"");
		str = StringUtil.replace(str, "<br>", "\n");
        // 替换跳格
		str = StringUtil.replace(str, "&nbsp;&nbsp;", "\t");
        // 替换空格
		str = StringUtil.replace(str, "&nbsp;", " ");
		return str;
	}
	/**
	 * 所有参数为空的时候返回true
	 * @param args
	 * @return true false
	 */
	public static Boolean isBlankAll(Object... args){
		Boolean flag=true;
		for (int i = 0; i < args.length; i++) {
			if(args[i] instanceof String){
				if(!isBlank((String) args[i])){
					flag=false;
				}
			}else{
				if(null!=args[i]){
					flag=false;
				}	
			}
		}
		return flag;
	}
	/**
	 * 所有参数为空的时候返回true
	 * @param args
	 * @return true false
	 */
	public static Boolean isBlank(Object obj){
		Boolean flag=true;
			if(obj instanceof String){
				if(!isBlank((String) obj)){
					flag=false;
				}
			}else{
				if(null!=obj){
					flag=false;
				}	
			}
		return flag;
	}
	/**
	 * 所有参数为空的时候返回true
	 * @param args
	 * @return true false
	 */
	public static Boolean isNotBlank(Object obj){
		Boolean flag=false;
			if(obj instanceof String){
				if(isNotBlank((String) obj)){
					flag=true;
				}
			}else{
				if(null!=obj){
					flag=true;
				}	
			}
		return flag;
	}
	/**
	 * 只要有一个参数为空就返回true
	 * @param args
	 * @return true false
	 */
	public static Boolean isBlankOne(Object... args){
		Boolean flag=false;
		for (int i = 0; i < args.length; i++) {
			if(args[i] instanceof String){
				if(isBlank((String) args[i])){
					flag=true;
				}
			}else{
				if(null==args[i]){
					flag=true;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 把字符串第一个字母转成大写
	 * @param str
	 * @return
	 */
	public static String getFirstUpper(String str){
		String newStr="";
		if(str.length()>0){
			newStr=str.substring(0, 1).toUpperCase()+str.substring(1, str.length());
		}
		return newStr;
	}
	/**
	 * 获取一个字符在一个字符串里出现的次数
	 * @param tagetStr
	 * @param str
	 * @return 
	 */
	public static int indexOfAll(String tagetStr,String str){
		int i=0;
		if(null!=tagetStr){
			i=tagetStr.length()-tagetStr.replace(str, "").length();
		}
		return i;
	}
	/**
	 * 转null字符串为""
	 * @param str
	 * @return
	 */
	public static String getNullTo(String str){
		if(isBlank(str)){
			str="";
		}
		return str;
	}
	/**
	 * 比较两个Long是否相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(Long a,Long b){
		boolean flag=false;
		if(null==a){
			a=0L;
		}
		if(null==b){
			b=0L;
		}
		if(a.equals(b)){
			flag=true;
		}
		return flag;
	}
	/**
	 * 比较两个对象是否相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(Object a,Object b){
		boolean flag=false;
		if(null==a){
			a="";
		}
		a=String.valueOf(a);
		if(null==b){
			b="";
		}
		b=String.valueOf(b);
		if(a.equals(b)){
			flag=true;
		}
		return flag;
	}
	/**
	 * 比较两个字符串是否相等
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean equals(String a,String b){
		boolean flag=false;
		if(null==a){
			a="";
		}
		if(null==b){
			b="";
		}
		if(a.equals(b)){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 检查字符串是否在数组中
	 * @Project SB
	 * @Package com.soft.sb.util
	 * @Method containsOnly方法.<br>
	 * @author 胡久洲
	 * @date 2013-9-18 上午9:09:07
	 * @param str
	 * @param valid
	 * @return
	 */
	public static boolean containsOnly(String str,String... valid){
		if ((valid == null) || (str == null)) {
			return false;
		}
		if (str.length() == 0) {
			return true;
		}
		if (valid.length == 0) {
			return false;
		}
		for(String s:valid){
			if(equals(str, s)){
				return true;
			}
		}
		return false;
	}
}
