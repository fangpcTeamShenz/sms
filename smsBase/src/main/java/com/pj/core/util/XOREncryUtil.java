/**
 * @author QSL
 * @date 2013-7-11
 */
package com.pj.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XOREncryUtil {
	private static String hexString = "0123456789ABCDEF";
	Log log = LogFactory.getLog(getClass().getName());
	/*
	 * 原文：123456789
	key:27 25 26 21 19 29 20 23
	加密后：2A2B2921262B232F22
	2A 2B 29 21 26 2B 23 2F 22
	 */
	public String XOREncry(String s){
		byte xKey[] = {Byte.parseByte( "27" ),Byte.parseByte("25" ),Byte.parseByte("26" ),Byte.parseByte("21" ),Byte.parseByte("19" ),Byte.parseByte("29" ),Byte.parseByte("20" ),Byte.parseByte("23" )};
		String result = "";
		int j = 0;
		int a = 0;
		String[] ss = encodes(s).split("@");
		int[] str = new int[ss.length] ;
		for(int i = 0;i<ss.length;i++){
			str[i]= Integer.parseInt(ss[i],16);
			a = (str[i] ^ xKey[j]);
			result = result + toHexStr(a);
			j = (j+1)% 8;
		}
		return result.toUpperCase();
	}
	
	/*
	 * 将字符串编码成16进制数字,适用于所有字符（包括中文）
	 */
	public static String encodes(String str) {
		// 根据默认编码获取字节数组
		byte[] bytes = str.getBytes();
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		// 将字节数组中每个字节拆解成2位16进制整数
		for (int i = 0; i < bytes.length; i++) {
			sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
			sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0)+"@");
		}
		return sb.toString();
	}
	
	/*
	 * 加密前：*80;:1234567890
	 * 加密后：	2A1C6CA78BC966B49AED86CBC100
	 * 
	 */
	public String Encode7bit(String src){
		String result = "" ;
		src = "*80;:"+src;
		log.debug("Encode7bit_before:"+src);

		int i=0,j=0,cur,len;
		String t;
		len = src.length();
		char[] srcarr = src.toCharArray();
		while(i<len){
		      if (i<len-1) {
		        cur=(char2AscII(srcarr[i]) >> j) | ((char2AscII(srcarr[i+1]) << (7-j)) & 255);
		      }else  {//  最后一位
		        cur=(char2AscII(srcarr[i]) >> j) & 127;
		      }
		      t=toHexStr(cur);
		      System.out.println("j:"+j+"__srcarr[i="+i+"]:"+srcarr[i]+"__cur:"+cur+"__t:"+t);
		      result=result+t;
		      i++;
		      j=(j+1) % 7;
		      if (j==0)
		        i++;
		}
		log.debug("result:"+result.toUpperCase());
		return result.toUpperCase();
		
	}
	
	/*
	 * int转换成两位16进制数
	 */
	private String toHexStr(int cur){
		String result = Integer.toHexString(cur);
		if(result.length()<2){
			result = "0" + result;
		}
		return result;
	}
	
	/*
	 * 字符转AscII码
	 */
	private static int char2AscII(char a){
		 return (int) a;   
	}
	
	/*
	 * 字符串转换为ASCII码   
	 */
	public static int[] string2ASCII(String s) {
        if (s == null || "".equals(s)) {   
            return null;   
        }   
  
        char[] chars = s.toCharArray();   
        int[] asciiArray = new int[chars.length];   
  
        for (int i = 0; i < chars.length; i++) {   
            asciiArray[i] = char2AscII(chars[i]);   
        }   
        return asciiArray;   
    } 
}
