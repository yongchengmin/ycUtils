package com.yc.utils.esbUtils;

/**
 * 字符串工具类
 * 
 */
public abstract class StringUtils {
	/**####*/
	public static String spilt4 = "####";
	/**#*/
	public static String spilt1 = "#";
	/**.*/
	public static String spiltDot = ".";
	public static String spiltComma = ",";
	/**¦*/
	public static String breakChar = "¦";
	/**\r\n 换行*/
	public static String enter = "\r\n";
	
	private StringUtils() {}

	/**
	 * 指定的字符串是否为空
	 * <ul>
	 * <li>SysUtils.isEmpty(null) = true</li>
	 * <li>SysUtils.isEmpty("") = true</li>
	 * <li>SysUtils.isEmpty("   ") = true</li>
	 * <li>SysUtils.isEmpty("abc") = false</li>
	 * </ul>
	 * 
	 * @param value 待检查的字符
	 * @return true/false
	 */
	public static boolean isEmpty(String value) {
		int strLen;
		if (value == null || (strLen = value.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(value.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
	public static String substringBeforeLast(String str, String separator){
		if ((isEmpty(str)) || (isEmpty(separator))) {
	      return str;
	    }
	    int pos = str.lastIndexOf(separator);
	    if (pos == -1) {
	      return str;
	    }
	    return str.substring(0, pos);
	}
	/**
	 * 对象是否为数字型字符�?
	 */
	public static boolean isNumeric(Object obj) {
		if (obj == null) {
			return false;
		}
		String str = obj.toString();
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 指定的字符串列表是否不为�?
	 */
	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}

	/**
	 * 把用字符编码的字符串转化为汉字编�?	 */
	public static String unicodeToChinese(String unicode) {
		StringBuilder out = new StringBuilder();
		if (!isEmpty(unicode)) {
			for (int i = 0; i < unicode.length(); i++) {
				out.append(unicode.charAt(i));
			}
		}
		return out.toString();
	}
	
	/**
	 * 过滤不可见字
	 */
	public static String stripNonValidXMLCharacters(String input) {
		if (input == null || ("".equals(input)))
			return "";
		StringBuilder out = new StringBuilder();
		char current;
		for (int i = 0; i < input.length(); i++) {
			current = input.charAt(i);
			if ((current == 0x9) || (current == 0xA) || (current == 0xD)
					|| ((current >= 0x20) && (current <= 0xD7FF))
					|| ((current >= 0xE000) && (current <= 0xFFFD))
					|| ((current >= 0x10000) && (current <= 0x10FFFF)))
				out.append(current);
		}
		return out.toString();
	}

	//参数 num:字符串长度  str:需要显示的字符
	public static String getStr(int num, String str){
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<num;i++){
		   sb.append(str);
		}
		return sb.toString();
	}
}
