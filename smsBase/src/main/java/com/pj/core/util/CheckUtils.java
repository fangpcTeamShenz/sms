package com.pj.core.util;

import org.apache.commons.lang.StringUtils;

public class CheckUtils {
	
	//运营商类型,参考CheckUtils类,运营商-移动
	public final static int  MOBILE_MOBILE = 1;
	
	//运营商类型,参考CheckUtils类,运营商-移动
	public final static int  MOBILE_UNICOM = 2;
	
	//运营商类型,参考CheckUtils类,运营商-移动
	public final static int  MOBILE_TELECOM = 3;
	
	public static String cm = "^((13[4-9])|(1705)|(147)|(15[0-2,7-9])|(172)|(178)|(18[2-4,7-8]))\\d{8}$"; // 移动
	public static String cmt = "^((1705)|(1703)|(1706))\\d{7}$"; // 移动虚拟
	public static String cu = "^((13[0-2])|(145)|(1704)|(170[7-9])|(15[5-6])|(171)|(175)|(176)|(18[5-6]))\\d{8}$";  // 联通
	public static String cut = "^((1704)|(170[7-9]))\\d{7}$";  // 联通虚拟
	public static String ct = "^((133)|(149)|(153)|(170[0-2])|(173)|(177)|(18[0-1,9]))\\d{8}$"; // 电信
	public static String ctt = "^((170[0-2]))\\d{7}$"; // 电信虚拟
	public static String fix = "^([1-99])+$";//固话
	public static String gj = "^00.*";//国际号码
	
	public static void main(String[] args) {
		String a = "17030873582";
		System.out.println(CheckIps(a));
	}
	public static Integer CheckIps(String number) {
		if (StringUtils.isBlank(number)) {
			return 4;
		}
		number = number.trim();
		if (number.matches(cm) || number.matches(cmt)) {
			return 1; // "中国移动"
		}
		if (number.matches(cu) || number.matches(cut)) {
			return 2; // "中国联通"
		}
		if (number.matches(ct) || number.matches(ctt)) {
			return 3; // "中国电信"
		}
		if (number.matches(fix)) {
			return 5; // 固话
		}
		if (number.matches(gj)) {
			return 6; // 国际
		}
		return 4;//未知
	}

	public static String CheckIpsChinese(String number) {
		if (StringUtils.isBlank(number)) {
			return null;
		}
		number = number.trim();
		if (number.matches(cm)) {
			return "移动"; // "中国移动"
		}
		if (number.matches(cu)) {
			return "联通"; // "中国联通"
		}
		if (number.matches(ct)) {
			return "电信"; // "中国电信"
		}
		if (number.matches(ct)) {
			return "固话"; // 固话
		}
		return null;
	}
	
	public static String CheckIps(Byte number) {
		if (number == 1) {
			return "移动"; // "中国移动"
		}
		if (number == 2) {
			return "联通"; // "中国联通"
		}
		if (number ==3) {
			return "电信"; // "中国电信"
		}
		return null;
	}
	
}
