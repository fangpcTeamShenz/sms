package com.pj.core.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class Signature {
	
	private static Logger log = Logger.getLogger(Signature.class);

	public static String getSign(Object o, String key) throws IllegalAccessException {
		ArrayList<String> list = new ArrayList<String>();
		Class<?> cls = o.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field f : fields) {
			f.setAccessible(true);
			if (f.get(o) != null && f.get(o) != "") {
				list.add(f.getName() + "=" + f.get(o) + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + key;
		return DigestUtils.md5Hex(result).toUpperCase();
	}

	public static String getSign(Map<String, String> map, String key) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (StringUtils.isNotBlank(entry.getValue())) {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + key;
		log.debug("签名原串："+result);
		return DigestUtils.md5Hex(result).toUpperCase();
	}
	
	public static boolean checkSignature(Map<String, String> map, String key) throws IllegalAccessException {
		
		if (StringUtils.isBlank(key)) {
			return false;
		}
		
		String signFromRequest = map.get("sign").toString();
				
		if (StringUtils.isBlank(signFromRequest)) {
			return false;
		}
		
		map.put("sign", "");
		
		String sign = getSign(map, key);
		log.debug("请求签名："+signFromRequest+"，验证签名："+sign);
		return sign.equals(signFromRequest);
	}
}
