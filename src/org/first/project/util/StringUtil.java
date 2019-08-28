package org.first.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 字符串工具类
 * 
 * @author 20824 2019年8月2日上午2:26:26
 */
public class StringUtil {
	public static boolean isEmpty(String str) {
		/**
		 * 判断是否是空
		 */
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 判断是否不是空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 是不是整型/长整型
	 * @param str
	 * @return
	 */
	public static boolean isLong(String str) {
		if(isEmpty(str))
			return false;
		for(int i=0;i<str.length();i++) {
			if(Character.isDigit(str.charAt(i)))
				return true;
		}
		return false;
	}
	/**
	 * 是不是日期
	 */
	public static boolean isDate(String str) {
		if(isEmpty(str))
			return false;
		SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
		try {
			//指定时间解析是否严格  false:严格
			sd.setLenient(false);
			//从给定的字符串,已生成日期
			sd.parse(str);
		}catch(ParseException e) {
			return false;
		}
		return true;
	}
	/*public static void main(String[] args) {
		System.out.println(check("1111-11-11"));
		System.out.println(check("1111-1-35"));
		System.out.println(check("1111-01-35"));
		System.out.println(check("1111-01-05"));
		System.out.println(check("1111-a1-35"));
	}*/

}
