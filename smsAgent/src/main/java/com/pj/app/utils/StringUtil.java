package com.pj.app.utils;

import com.pj.core.util.MD5Util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
public class StringUtil extends Util {
	/**
	 * 截取小数位数
	 * 
	 * @param f
	 * @param lenght
	 * @return
	 */
	public static float subFloat(float f, int lenght) {
		String fStr = String.valueOf(f);
		int i = fStr.indexOf('.');
		String returnStr = fStr;
		if (fStr.length() > i + 1 + lenght)
			returnStr = fStr.substring(0, i + 1 + lenght);
		float returnf = (Float.valueOf(returnStr)).floatValue();
		return returnf;
	}
	
	/**
	 * 货币数据格式化（3位逗号分隔）
	 * @param str
	 * @param start
	 * @param length
	 * @return
	 */
	public  String moneyNumFormat(double ins){
		NumberFormat numberFormat = NumberFormat.getNumberInstance(); 
        numberFormat.setMinimumFractionDigits(3); 
        String numberString = numberFormat.format(ins); 
		return numberString;
	}
	
	public float moneyType2Float(String moneystr){
		if(moneystr!=null){
		String restr = moneystr.replace(",", "").replace("￥", "");
			return Float.parseFloat(restr);
		}else
			return 0;
	}
	public String moneyType2String(String moneystr){
		if(moneystr!=null){
			String restr = moneystr.replace(",", "").replace("￥", "");
			return restr;
		}else
			return "0";
	}

	/**
	 * 截取字符串长度
	 * 
	 * @param str
	 * @param start
	 * @param length
	 * @return
	 */
	public static String getSubStr(String str, int start, int length) {
		if (str == null)
			return "";
		if (start < 0)
			return "";
		int end = str.getBytes().length;
		if (start + length < end) {
			end = start + length;
			return str.substring(start, end);
		}
		return str.substring(start, end);
	}

	/**
	 * 截取字符串长度
	 * 
	 * @param str
	 * @param start
	 * @param length
	 * @return
	 */
	public static synchronized String getSubStr(String str, int length) {
		String stra = str;

		if (length == 0) {
			return stra;

		}
		if (stra == null)
			return "";
		int start = 0;
		int end = stra.length();

		if (end <= length) {
			return stra;
		}

		if (length < end) {

			try {
				stra = stra.substring(start, length) + "..";

			} catch (Exception e) {

				stra = stra.substring(start, 5) + "..";

			}

		}
		return stra;

	}

	/**
	 * 替换字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String replaceHtmlString(String src) {
		if (src == null)
			return "";
		src = src.replaceAll("<p>", "");
		src = src.replaceAll("</p>", "");
		src = src.replaceAll("<strong>", "");
		src = src.replaceAll("</strong>", "");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll("&", "&amp;");
		src = src.replaceAll("%", "％");
		src = src.replaceAll("$", "");
		src = src.replaceAll("'", "＇");
		return src;
	}

	/**
	 * MD5加密
	 * 
	 * @param src
	 * @return
	 */
	public static String md5String(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			// 如果输入“SHA”，就是实现SHA加密。
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
			return new String(str).toLowerCase();
		} catch (Exception e) {
			return null;
		}

	}

	public static Long StringToLong(String sid) {

		if (sid != null && !"".equals(sid)) {
			try {

				return Long.parseLong(sid);
			} catch (Exception e) {
				return null;
			}

		} else {
			return null;

		}

	}

	/**
	 * 功能：十进制数到十六进制
	 * 
	 * @param value
	 * @return
	 */
	public String Integer2HexString(int value) {
		return Integer.toHexString(value);
	}

	/**
	 * 功能：十进制数到八进制
	 * 
	 * @param value
	 * @return
	 */
	public String Integer2OctalString(int value) {
		return Integer.toOctalString(value);
	}

	/**
	 * 功能：十进制数到二进制
	 * 
	 * @param value
	 * @return
	 */
	public String Integer2BinaryString(int value) {
		return Integer.toBinaryString(value);
	}

	/**
	 * 功能：十六进制数到十进制
	 * 
	 * @param value
	 * @return
	 */
	public int HexString2Integer(String value) {
		return Integer.parseInt(value, 16);
	}

	/**
	 * 功能：八进制数到十进制
	 * 
	 * @param value
	 * @return
	 */
	public int OctalString2Integer(String value) {
		return Integer.parseInt(value, 8);
	}

	/**
	 * 功能：八进制数到十进制
	 * 
	 * @param value
	 * @return
	 */
	public int BinaryString2Integer(String value) {
		return Integer.parseInt(value, 2);
	}

	/**
	 * 功能：字符串进行MD5编码，返回16进制字符串
	 * 
	 * @param message
	 * @return
	 */
	public String md5Encode(String message) {
		return EncodeString2Hex("MD5", message);
	}

	/**
	 * 功能：字符串进行SHA编码，返回16进制字符串
	 * 
	 * @param message
	 * @return
	 */
	public String shaEncode(String message) {
		return EncodeString2Hex("SHA", message);
	}

	/**
	 * 功能：字符串进行SHA-256编码，返回16进制字符串
	 * 
	 * @param message
	 * @return
	 */
	public String sha256Encode(String message) {
		return EncodeString2Hex("SHA-256", message);
	}

	/**
	 * 功能：字符串进行SHA-512编码，返回16进制字符串
	 * 
	 * @param message
	 * @return
	 */
	public String sha512Encode(String message) {
		return EncodeString2Hex("SHA-512", message);
	}

	/**
	 * 功能：将字符串转为16进制字符串表示
	 * 
	 * @param src
	 * @return
	 */
	public static String String2Hex(String src) {
		if (src == null)
			return null;
		byte[] b = src.getBytes();
		StringBuffer sb = new StringBuffer(b.length);
		String sTemp;
		for (int i = 0; i < b.length; i++) {
			sTemp = Integer.toHexString(0xFF & b[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();

		// if(src==null)
		// return null;
		// byte[] b=src.getBytes();
		// String ret = "";
		// for (int i = 0; i < b.length; i++) {
		// String hex = Integer.toHexString(b[i] & 0xFF);
		// if (hex.length() == 1) {
		// hex = '0' + hex;
		// }
		// ret += hex.toUpperCase();
		// }
		// return ret;
	}

	/**
	 * 功能：将字符串进行编码，并返回字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String EncoderString(String code, String src) {
		try {
			byte[] strTemp = src.getBytes();
			MessageDigest messageDigest = MessageDigest.getInstance(code);
			messageDigest.update(strTemp);
			byte[] md = messageDigest.digest();
			return byteArray2String(md);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 功能：将字节数组转为字符串
	 * 
	 * @param byteArray
	 * @return encode
	 */
	private static String byteArray2String(byte[] byteArray) {
		String encode = null;
		int j = byteArray.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = byteArray[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		encode = new String(str);
		return encode;
	}

	/**
	 * 功能：将字符串进行编码，并返回16进制字符串
	 * 
	 * @param code
	 * @param message
	 * @return encode
	 */
	private String EncodeString2Hex(String code, String message) {
		MessageDigest md;
		String encode = null;
		try {
			md = MessageDigest.getInstance(code);
			encode = byteArrayToHexString(md.digest(message.getBytes()));
		} catch (NoSuchAlgorithmException e) {

		}
		return encode;
	}

	/**
	 * 功能：对字节数组编码
	 * 
	 * @param byteArray
	 * @return
	 */

	private String byteArrayToHexString(byte[] byteArray) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			sb.append(byteToHexString(byteArray[i]));
		}
		return sb.toString();
	}

	/**
	 * 功能：将字节进行16进制编码
	 * 
	 * @param byt
	 * @return
	 */
	private String byteToHexString(byte byt) {
		int n = byt;
		if (n < 0)
			n = 256 + n;
		return String.valueOf(hexDigits[n / 16] + hexDigits[n % 16]);
	}

	private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static String getFileSizeAutoFormate(Long hasfileTotalSize) {

		if (hasfileTotalSize > 1024 * 1024) {
			return (hasfileTotalSize / 1024) + "MB";

		} else if (hasfileTotalSize > 1024 * 1024 * 1024) {

			return (hasfileTotalSize / (1024 * 1024 * 1024)) + "GB";

		} else {

			return hasfileTotalSize + "KB";
		}
	}

	public static int StringToInt(String str) {

		if (str != null && !"".equals(str)) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				return 0;
			}

		} else {
			return 0;

		}

	}

	public static String removeEndByStr(String str, String end) {

		if (str.lastIndexOf(end) == -1) {
			return str;

		}
		return str.substring(0, str.lastIndexOf(end));
	}

	public static String StringToUT8(String title) {

		String str = null;
		if (title != null) {

			try {
				str = new String(title.getBytes("iso-8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				return null;
			}
		}
		return str;
	}

	public static Float StringToFloat(String str) {
		if (str != null && !"".equals(str)) {
			try {
				return Float.parseFloat(str);
			} catch (Exception e) {
				return 0f;
			}

		} else {
			return 0f;

		}
	}

	/**
	 * 得到货币型的少数点位数
	 * 
	 * @param doubleValue
	 * @param point
	 * @return
	 */
	public static String doubleToStr1Point(double doubleValue, int point) {
		try {
			double double_value = doubleValue;
			int point_value = point;
			if (double_value == 0) {
				double_value = 0;
			} else {
				BigDecimal bdl = new BigDecimal(double_value);
				bdl = bdl.setScale(point_value, BigDecimal.ROUND_HALF_EVEN);
				double_value = bdl.doubleValue();
			}
			return String.valueOf(double_value);

		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * 得到本周开始日期
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getWeekStartDate() {
		String startDate = null;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		int w = c1.get(java.util.Calendar.DAY_OF_WEEK);
		if (w == 1) {
			w = w - 8;
		}
		if (w > 1) {
			w = -(w - 1);
		}
		c1.add(c1.DATE, w);
		startDate = format.format((c1.getTime()));
		return startDate;
	}

	/**
	 * 得到本周结束日期
	 * 
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getWeekEndDate() {
		String endDate = null;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		int w = c1.get(java.util.Calendar.DAY_OF_WEEK);
		if (w == 1) {
			w = 8 - w;
		}
		if (w > 1) {
			w = 7 - w;
		}
		c1.add(c1.DATE, w);
		endDate = format.format((c1.getTime()));
		return endDate;
	}

	/**
	 * 得到提前天数的日期
	 * 
	 * @param day
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getPreDate(int day) {
		String startDate = null;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		c1.add(c1.DATE, day);
		startDate = format.format((c1.getTime()));
		return startDate;
	}

	/**
	 * 得到提前月数的日期
	 * 
	 * @param day
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getPreMonth(int month) {
		String startDate = null;
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		c1.add(c1.MONTH, month);
		startDate = format.format((c1.getTime()));
		return startDate;
	}

	/**
	 * 替换字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String replaceString(String src) {
		if (src == null)
			return "";
		src = src.replaceAll("<p>", "");
		src = src.replaceAll("</p>", "");
		src = src.replaceAll("<strong>", "");
		src = src.replaceAll("</strong>", "");
		src = src.replaceAll(">", "&gt;");
		src = src.replaceAll("<", "&lt;");
		src = src.replaceAll("&", "&amp;");
		src = src.replaceAll("%", "％");
		src = src.replaceAll("$", "");
		src = src.replaceAll("'", "＇");
		return src;
	}

	/**
	 * 把字串中的;号过滤，提取一个字符串数组
	 * 
	 * @param source
	 *            要过滤的字符串
	 * @return 返回的字符串数组
	 */
	public static String[] getSArray(String source) {
		List<String> temp = new ArrayList<String>();
		String[] ret = null;
		try {
			StringTokenizer st = new StringTokenizer(source, ";");
			while (st.hasMoreTokens()) {
				String path = st.nextToken();
				if (null != path && path.trim().length() > 0) {
					temp.add(path);
				}
			}
			ret = new String[temp.size()];
			int i = 0;
			while (i < temp.size()) {
				ret[i] = (String) temp.get(i);
				i++;
			}
		} catch (Exception ex) {

		} finally {
			temp.clear();
			temp = null;
		}
		return ret;
	}

	/**
	 * 去掉符串null
	 * 
	 * @param str
	 * @return
	 */
	public static String filterNull(String str) {
		if (str == null)
			return "";
		return str;
	}

	public static String getRandomPassword() {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyz");
		StringBuffer password = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < 6; i++) {
			password.append(buffer.charAt(r.nextInt(range)));
		}
		return password.toString();
	}
	
	/**
	 * @param strings
	 * @return
	 * @author Linxs
	 * 连接传入的字符串
	 * 日期：2012-11-27
	 */
	public static String joinString(String...strings) {
		StringBuilder result = new StringBuilder();
		for(String current : strings) {
			result.append(current);
		}
		return result.toString();
	}
	
	/**
	 * @return
	 * @author Linxs
	 * 生成唯一的UUID
	 * 日期：2012-11-28
	 */
	public static String generateUUID() {
		return UUID.randomUUID().toString().replace("-", StringUtils.EMPTY);
	}
	
	public static String concat(String... strings ) {
		String result = "";
		for(String item : strings) {
			result += item;
		}
		return result; 
		
	}

	/**
	 * 判断该字符串是否是数字
	 * @param idStr
	 * @return
	 */
	public static boolean isNum(String idStr) {
		try {
			new BigDecimal(idStr);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	public static Map<String, String> formatSocketMsg(String socketMessage) {
		String[] split = socketMessage.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for(String str : split){
			String[] msgs = str.split("=");
			if(msgs.length<2){
				map.put(msgs[0], null);
			}else{
				map.put(msgs[0], msgs[1]);
			}
		}
		return map;
	}
	
	/*
	 * 判断鉴权模式是否为SessionTOken
	 */
	public String StrCommonMd5(String validate,String actionStr, String pwdStr,String localip) {
		String sign,resultstr;
		if(validate.length()>6){//缓存验证码大于4位，表明，umm缓存为sessionToken鉴权模式
			sign = MD5UtilStr(actionStr +  "&ClientIP="+localip+"&NetType=WEB&Password="
					+ pwdStr).toLowerCase();
			resultstr = actionStr +  "&ClientIP="+localip+"&NetType=WEB&Sign="
			+ sign;
		}else{
			sign = MD5UtilStr(actionStr + "&VToken=" + validate + "&ClientIP="+localip+"&NetType=WEB&Password="
				+ pwdStr).toLowerCase();
			resultstr = actionStr + "&VToken=" + validate + "&ClientIP="+localip+"&NetType=WEB&Sign="
				+ sign;
		}
		return resultstr;
	}
	
	public String StrCommon(String actionStr, String pswStr,String localip) {
		String random4 = getRandom4Str();
		String mm2 = MD5UtilStr(random4 + pswStr).toLowerCase();
		String msg = actionStr + "&VToken=" + random4 + "&ClientIP="+localip+"&NetType=WEB&Password="
		+ mm2;
		String sign = MD5UtilStr(msg).toLowerCase();
		String resultstr = actionStr + "&VToken=" + random4 + "&ClientIP="+localip+"&NetType=WEB&Sign="
		+ sign;
		return resultstr;
	}

	public static String MD5UtilStr(String str1) {
		String md5result = MD5Util.MD5Encode(str1, "gbk").toString().trim();
		return md5result.toLowerCase();
	}

	public String addLength(String str) {
		String resultstr = "";
		int mesleg = 0;
		try {
			mesleg = str.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (mesleg < 10) {
			resultstr = "00" + mesleg + str;

		} else if ((mesleg >= 10) && (mesleg < 100)) {
			resultstr = "0" + mesleg + str;
		} else {
			resultstr = mesleg + str;
		}
		return resultstr;

	}

	public String changeFromDate(String date) {
		Date start = new Date();
		String starttime = null;
		String midparam = date + " 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (date.equals("") || date == null) {
				starttime = sdf.format(start);
			} else {
				start = sdf.parse(midparam);
				//sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
				starttime = sdf.format(start);
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return starttime;
	}
	

	public String changeToDate(String date) {
		Date start = new Date();
		String starttime = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (date.equals("") || date == null) {
				starttime = sdf.format(start);
			} else {
				start = sdf.parse(date + " 23:59:59");
				//sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
				starttime = sdf.format(start);
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return starttime;
	}

	public static String getEasyRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefgABCDEFG0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	public static String getRandom4Str() {
		return getRandomString(4);
	}
    
	/*
	 * 返回Errormsg和RetMsg的拼接值；
	 */
    public static String splitReturnMsg(String ins){
    	String returnstr = "fail" ;
    	if(ins.contains("&Errormsg=")){
    		returnstr = ins.substring(ins.indexOf("&Errormsg=") + 10).split("&")[0];
    		if(ins.contains("RetMsg=")){
    			returnstr = returnstr+","+ins.substring(ins.indexOf("RetMsg=") + 7).split("&")[0];
    		}
    	}
		return returnstr;
    }

	/**
	*@author li.xt
	*@date 2014-4-9上午11:05:25
	*@Description:获取时间字符串
	*@param date
	*@param string
	*@return String
	*/
	public static String getTimeAsString(Date date, String string) {
		return new SimpleDateFormat(string).format(date);
	}

	/**
	*@author li.xt
	*@date 2014-5-15上午11:02:25
	*@param queryResult
	*@return
	*@return String
	*/
	public static String getEventMessage(String queryResult) {
		Map<String, String> map = new HashMap<String, String>();
		map = splitReMsg(queryResult);
		String message = "";
		if(map.get("Errormsg")!=null){
			message = map.get("Errormsg")+" ";
		}
		if(map.get("RetMsg")!=null){
			message = map.get("RetMsg");
		}
		return message;
	}
	
	public static Map<String, String> splitReMsg(String ins){
		Map<String, String> mapreturn = new HashMap<String, String>();
		String[] split = ins.split("&");
		int locate;
		for(String msg : split){
			locate = msg.indexOf("=");
			String a = msg.substring(0, locate);
			String b = msg.substring(locate+1);
			mapreturn.put(a, b);
			//System.out.println("msg:"+msg+" locate:"+locate);
			//System.out.println(a+":"+b);
		}
		return mapreturn;
	}
	
	public static String getFilterString(String str){
		return str.replaceAll("[\\t\\n\\r]", "");
	}

}
