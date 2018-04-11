package com.pj.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 时间工具类
 * @author Fangpc
 *
 */
public class DateUtils {

	/** 定义常量 **/
	public static final String DATE_JFP_STR = "yyyyMM";
	public static final String DATE_FULL_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SMALL_STR = "yyyy-MM-dd";
	public static final String DATE_KEY_STR = "yyMMddHHmmss";
	public static final String DATE_FORMAT_ONE="yyyy.MM.dd";
	public static final String DATE_FORMAT_TWO="yyyy-MM-dd"; 
    //一天的毫秒数 86400000 = 24*60*60*1000;
    private static final int millisPerDay = 86400000 ;
    //一小时的毫秒数 3600000 = 24*60*60*1000;
    private static final int millisPerHour = 3600000 ;
    public static void main(String[] args) {
		System.out.println(DateUtils.format(new Date(1497436146000L)) );
	}
    
    /**
     * 格式化时间
     * @param strDate
     * @return
     */
    public static String format(Date strDate) {
    	SimpleDateFormat format = new SimpleDateFormat(DATE_FULL_STR); 
		return format.format(strDate);
	} 
    
    /**
     * 格式化时间
     * @param strDate
     * @return
     */
    public static String format(Date strDate, String pattern) {
    	SimpleDateFormat format = new SimpleDateFormat(pattern); 
		return format.format(strDate);
	} 
    
	/**
	 * 使用预设格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, DATE_FULL_STR);
	}

	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 两个时间比较
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDateWithNow(Date date1) {
		Date date2 = new Date();
		int rnum = date1.compareTo(date2);
		return rnum;
	}

	/**
	 * 两个时间比较(时间戳比较)
	 * 
	 * @param date
	 * @return
	 */
	public static int compareDateWithNow(long date1) {
		long date2 = dateToUnixTimestamp();
		if (date1 > date2) {
			return 1;
		} else if (date1 < date2) {
			return -1;
		} else {
			return 0;
		}
	}
	
	/**
	 * 计算时间差 (时间单位,开始时间,结束时间) 调用方法
	 * howLong("h","2007-08-09 10:22:26","2007-08-09 20:21:30") ///9小时56分 返回9小时
	 * */
	public static long howLong(String unit, String time1, String time2)
			throws ParseException {
		// 时间单位(如：不足1天(24小时) 则返回0)，开始时间，结束时间
		Date date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time1);
		Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time2);
		long ltime = date1.getTime() - date2.getTime() < 0 ? date2.getTime()
				- date1.getTime() : date1.getTime() - date2.getTime();
		if (unit.equals("s")) {
			return ltime / 1000;// 返回秒
		} else if (unit.equals("m")) {
			return ltime / 60000;// 返回分钟
		} else if (unit.equals("h")) {
			return ltime / millisPerHour;// 返回小时
		} else if (unit.equals("d")) {
			return ltime / millisPerDay;// 返回天数
		} else {
			return 0;
		}
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_FULL_STR);
		return df.format(new Date());
	}

	/**
	 * 获取系统当前时间
	 * 
	 * @return
	 */
	public static String getNowTime(String type) {
		SimpleDateFormat df = new SimpleDateFormat(type);
		return df.format(new Date());
	}

	/**
	 * 获取系统当前计费期
	 * 
	 * @return
	 */
	public static String getJFPTime() {
		SimpleDateFormat df = new SimpleDateFormat(DATE_JFP_STR);
		return df.format(new Date());
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param String
	 *            date 需要转换的日期 yyyy-MM-dd HH:mm:ss
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(DATE_FULL_STR).parse(date)
					.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * 将指定的日期转换成Unix时间戳
	 * 
	 * @param String
	 *            date 需要转换的日期 yyyy-MM-dd
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp(String date, String dateFormat) {
		long timestamp = 0;
		try {
			timestamp = new SimpleDateFormat(dateFormat).parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	/**
	 * 将当前日期转换成Unix时间戳
	 * 
	 * @return long 时间戳
	 */
	public static long dateToUnixTimestamp() {
		long timestamp = new Date().getTime();
		return timestamp;
	}

	/**
	 * 将Unix时间戳转换成日期
	 * 
	 * @param long timestamp 时间戳
	 * @return String 日期字符串
	 */
	public static String unixTimestampToDate(long timestamp) {
		SimpleDateFormat sd = new SimpleDateFormat(DATE_FULL_STR);
		sd.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		return sd.format(new Date(timestamp));
	}
	/**
	 * 获取本月最后一天
	 * @return
	 */
	public static String getDateByMonth(String dateType){
		Calendar calendar = Calendar.getInstance();  
		// 设置时间,当前时间不用设置  
		// calendar.setTime(new Date());  
		// 设置日期为本月最大日期  
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));  
		SimpleDateFormat format = new SimpleDateFormat(dateType); 
		return  format.format(calendar.getTime());  
	}
	
	 /**
     * 当月第一天
     * @return
     */
	public static String getFirstDay(String dateType) {
        SimpleDateFormat format = new SimpleDateFormat(dateType);
        Calendar calendar = Calendar.getInstance();
        Date theDate = calendar.getTime();
        
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        return  format.format(gcLast.getTime());
    }
	
	public static Date addMinuteTime(Date beginTime,Integer minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(beginTime);
		cal.add(Calendar.MINUTE, minute);
		Date endTime = cal.getTime();
		return endTime;
	}
}
