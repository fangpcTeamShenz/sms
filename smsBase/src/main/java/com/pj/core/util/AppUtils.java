package com.pj.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

/**
 * 
 * @ClassName: AppUtils
 * @Description: 程序工具类，提供大量的便捷方法
 *
 */
public class AppUtils {

	private static Logger log = Logger.getLogger(AppUtils.class);
	
    /**
     * 产生一个36个字符的UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 产生一个固定长度的随机字符串
     * @param len 长度
     * @return
     */
    public static String randomStr(int len) {
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < len) {
			// 生成随机数，取绝对值，防止生成负数
			i = Math.abs(r.nextInt(str.length)); // 生成的数最大为36-1
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
    }
    /**
     * 产生一个固定长度的随机数字字符串
     * @param len 长度
     * @return
     */
    public static String randomNumber(int len) {
		int i; // 生成的随机数
		int count = 0; // 生成的密码的长度
		char[] str = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < len) {
			// 生成随机数，取绝对值，防止生成负数
			i = Math.abs(r.nextInt(str.length));
			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}
		return pwd.toString();
    }
    /**
     * md5加密
     *
     * @param value 要加密的值
     * @return md5加密后的值
     */
    public static String md5Hex(String value) {
        return DigestUtils.md5Hex(value);
    }

    /**
     * sha1加密
     *
     * @param value 要加密的值
     * @return sha1加密后的值
     */
    public static String sha1Hex(String value) {
        return DigestUtils.sha1Hex(value);
    }

    /**
     * sha256加密
     *
     * @param value 要加密的值
     * @return sha256加密后的值
     */
    public static String sha256Hex(String value) {
        return DigestUtils.sha256Hex(value);
    }
    	
	/**
	 * 获得真实IP地址，如果通过代理进来，则透过防火墙获取真实IP地址;
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_CLIENT_IP");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			}
			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
			}
		} else if (ip.length() > 15) {
			String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }
		}
		return ip;
	}
	
	/**
	 * date转 String 格式化日期
	 */
	public static String formatDateToString(Date date,String format) {
		return new SimpleDateFormat(format).format(date);
	}
	
	public static Date formatStringToDate(String date,String format){
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			log.info("String转Date类型失败"+e);
			return null;
		}
	}
	
	/**
	 * 当前月份
	 */
	@SuppressWarnings("deprecation")
	public static String getMonths(){
		Date currTime = new Date();
		int month = currTime.getMonth()+1;//月
		return month+"";
	}
	
	public static synchronized String getOrderNo(Integer index) {
		String order = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		Random r = new Random();
		for (int i = 0; i < index; i++) {
			order += r.nextInt(9);
		}
		return order;
	}

	public static String getURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int start = uri.indexOf(contextPath);
		return uri.substring(start + contextPath.length());
	}
}
