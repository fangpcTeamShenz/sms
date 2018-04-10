package com.pj.core.util;


/**
 * 
 * @author Fangpc
 *
 */
public class IPv4LongUtil {

	public static String iplongToIp(long ipaddress) {
		StringBuffer sb = new StringBuffer("");
		sb.append(String.valueOf((ipaddress >>> 24)));
		sb.append(".");
		sb.append(String.valueOf((ipaddress & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((ipaddress & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf((ipaddress & 0x000000FF)));
		return sb.toString();
	}

	public static long ipStrToLong(String ipaddress) {
		long[] ip = new long[4];
		int position1 = ipaddress.indexOf(".");
		int position2 = ipaddress.indexOf(".", position1 + 1);
		int position3 = ipaddress.indexOf(".", position2 + 1);
		ip[0] = Long.parseLong(ipaddress.substring(0, position1));
		ip[1] = Long.parseLong(ipaddress.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(ipaddress.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(ipaddress.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(IPv4LongUtil.ipStrToLong("182.123.124.123"));
		System.out.println(IPv4LongUtil.iplongToIp(3061546107L));
	}
}
