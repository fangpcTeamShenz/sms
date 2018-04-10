package com.pj.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.apache.commons.logging.LogFactory;
import java.util.GregorianCalendar;
import org.apache.commons.logging.Log;
import java.util.Date;
import java.util.Calendar;

public class DateUtil {
	 private static Log log = LogFactory.getLog(DateUtil.class);

	    private static String datePattern = "yyyy-MM-dd";

	    private static String timePattern = "HH:mm";

	    /**
	     * Return 缺省的日期格式 (yyyy/MM/dd)
	     *
	     * @return 在页面中显示的日期格式
	     */
	    public static String getDatePattern()
	    {

	        return datePattern;
	    }
	   
	    /**
	     * 根据日期格式，返回日期按datePattern格式转换后的字符串
	     *
	     * @param aDate
	     *            日期对象
	     * @return 格式化后的日期的页面显示字符串
	     */
	    public static final String getDate(Date aDate)
	    {
	        SimpleDateFormat df = null;
	        String returnValue = "";
	        if (aDate != null)
	        {
	            df = new SimpleDateFormat(datePattern);
	            returnValue = df.format(aDate);
	        }
	        return (returnValue);
	    }
	    public static final String getDate(String pattern)
	    {
	        Date date = new Date();
	        return getDate(date, pattern);
	    }
	    public static final String getDate(Date date, String pattern)
	    {
	        SimpleDateFormat df = null;
	        String returnValue = "";
	        if (date != null)
	        {
	            df = new SimpleDateFormat(pattern);
	            returnValue = df.format(date);
	        }
	        return (returnValue);
	    }
	    public static Date getDate(String dateString, String pattern)
	    {
	        SimpleDateFormat df = null;
	        Date date = new Date();
	        if (dateString != null)
	        {
	            try
	            {
	                df = new SimpleDateFormat(pattern);
	                date = df.parse(dateString);
	            }
	            catch(Exception e)
	            {}
	        }
	        return date;
	    }
	    /**
	     * 按照日期格式，将字符串解析为日期对象
	     *
	     * @param aMask
	     *            输入字符串的格式
	     * @param strDate
	     *            一个按aMask格式排列的日期的字符串描述
	     * @return Date 对象
	     * @see java.text.SimpleDateFormat
	     * @throws ParseException
	     */
	    public static final Date convertStringToDate(String aMask, String strDate)
	    {
	        SimpleDateFormat df = null;
	        Date date = null;
	        df = new SimpleDateFormat(aMask);
	        if (log.isDebugEnabled())
	        {
	        	log.info("DateUtil_convertStringToDate_converting '" + strDate + "' to date with mask '" + aMask + "'");
	        }
	        try
	        {
	            date = df.parse(strDate);
	        }
	        catch (ParseException pe)
	        {}
	        return (date);
	    }

	    /**
	     * This method returns the current date time in the format: yyyy/MM/dd HH:MM
	     * a
	     *
	     * @param theTime
	     *            the current time
	     * @return the current date/time
	     */
	    public static String getTimeNow(Date theTime)
	    {
	        return getDateTime(timePattern, theTime);
	    }

	    /**
	     * This method returns the current date in the format: yyyy/MM/dd
	     *
	     * @return the current date
	     * @throws ParseException
	     */
	    public static Calendar getToday() throws ParseException
	    {
	        Date today = new Date();
	        SimpleDateFormat df = new SimpleDateFormat(datePattern);
	      
	        String todayAsString = df.format(today);
	        Calendar cal = new GregorianCalendar();
	        cal.setTime(convertStringToDate(todayAsString));
	        return cal;
	    }
	    /**
	     * This method generates a string representation of a date's date/time in
	     * the format you specify on input
	     *
	     * @param aMask
	     *            the date pattern the string is in
	     * @param aDate
	     *            a date object
	     * @return a formatted string representation of the date
	     *
	     * @see java.text.SimpleDateFormat
	     */
	    public static final String getDateTime(String aMask, Date aDate)
	    {
	        SimpleDateFormat df = null;
	        String returnValue = "";
	        if (aDate == null)
	        {
	            log.error("aDate is null!");
	        }
	        else
	        {
	            df = new SimpleDateFormat(aMask);
	            returnValue = df.format(aDate);
	        }
	        return (returnValue);
	    }
	    /**
	     * 根据日期格式，返回日期按datePattern格式转换后的字符串
	     * @param aDate Date
	     * @return String
	     */
	    public static final String convertDateToString(Date aDate)
	    {
	        return getDateTime(datePattern, aDate);
	    }
	    /**
	     * 按照日期格式，将字符串解析为日期对象
	     * @param strDate String
	     * @return Date
	     * @throws ParseException
	     */
	    public static Date convertStringToDate(String strDate)
	    {
	        Date aDate = null;
	        if (log.isDebugEnabled())
	        {
	        	log.info("DateUtil_convertStringToDate_converting date with pattern: " + datePattern);
	        }
	        aDate = convertStringToDate(datePattern, strDate);
	        return aDate;
	    }

	    public static String getYear()
	    {
	        Date date = new Date();
	        return getDate(date, "yyyy");
	    }
	    public static String getMonth()
	    {
	        Date date = new Date();
	        return getDate(date, "MM");
	    }
	    public static String getDay()
	    {
	        Date date = new Date();
	        return getDate(date, "dd");
	    }
	    /**
	     * 返回小时
	     *
	     * @param date
	     * 日期
	     * @return 返回小时
	     */
	    public static int getHour(java.util.Date date)
	    {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        return c.get(java.util.Calendar.HOUR_OF_DAY);
	    }
	    /**
	     * 返回分钟
	     *
	     * @param date
	     * 日期
	     * @return 返回分钟
	     */
	    public static int getMinute(java.util.Date date)
	    {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        return c.get(java.util.Calendar.MINUTE);
	    }
	    /**
	     * 返回秒钟
	     *
	     * @param date
	     * 日期
	     * @return 返回秒钟
	     */
	    public static int getSecond(java.util.Date date)
	    {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        return c.get(java.util.Calendar.SECOND);
	    }
	    /**
	     * 返回毫秒
	     *
	     * @param date
	     * 日期
	     * @return 返回毫秒
	     */
	    public static long getMillis(java.util.Date date)
	    {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTime(date);
	        return c.getTimeInMillis();
	    }
	    /**
	     * 日期相加
	     *
	     * @param date
	     * 日期
	     * @param day
	     * 天数
	     * @return 返回相加后的日期
	     */
	    public static java.util.Date addDate(java.util.Date date, int day)
	    {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
	        return c.getTime();
	    }
	    /**
	     * 日期相减
	     *
	     * @param date
	     * 日期
	     * @param day
	     * 天数
	     * @return 返回相减后的日期
	     */
	    public static java.util.Date diffDate(java.util.Date date, int day)
	    {
	        java.util.Calendar c = java.util.Calendar.getInstance();
	        c.setTimeInMillis(getMillis(date) - ((long) day) * 24 * 3600 * 1000);
	        return c.getTime();
	    }
	    /**
	     * 日期相减
	     *
	     * @param date
	     * 日期
	     * @param date1
	     * 日期
	     * @return 返回相减后的天数
	     */
	    public static int diffDate(java.util.Date date, java.util.Date date1)
	    {
	        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
	    }
	    public static int diffDateToHour(Date date, Date date1)
	    {
	        return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60* 60));
	    }
	    
	    public static String getTodayDatePass(int passDay) {
			Calendar calendar = Calendar.getInstance();
			Date date = new Date();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, -passDay);
			return Util.formatDate(calendar.getTime(), "yyyy-MM-dd");
		}
		
		/**
		  * 取得指定日期的所处星期的第一天
		  * 
		  * @param date
		  *            指定日期。
		  * @return 指定日期的所处星期的第一天
		  */
		 public static synchronized java.util.Date getFirstDayOfWeek(
		  java.util.Date date )
		 {
		  /**
		   * 详细设计： 
		   * 1.如果date是星期日，则减0天 
		   * 2.如果date是星期一，则减1天 
		   * 3.如果date是星期二，则减2天
		   * 4.如果date是星期三，则减3天 
		   * 5.如果date是星期四，则减4天 
		   * 6.如果date是星期五，则减5天
		   * 7.如果date是星期六，则减6天
		   */
		  GregorianCalendar gc = ( GregorianCalendar ) Calendar.getInstance();
		  gc.setTime( date );
		  switch ( gc.get( Calendar.DAY_OF_WEEK ) )
		  {
		   case ( Calendar.SUNDAY  ):
		    gc.add( Calendar.DATE, 0 );
		    break;
		   case ( Calendar.MONDAY  ):
		    gc.add( Calendar.DATE, -1 );
		    break;
		   case ( Calendar.TUESDAY  ):
		    gc.add( Calendar.DATE, -2 );
		    break;
		   case ( Calendar.WEDNESDAY  ):
		    gc.add( Calendar.DATE, -3 );
		    break;
		   case ( Calendar.THURSDAY  ):
		    gc.add( Calendar.DATE, -4 );
		    break;
		   case ( Calendar.FRIDAY  ):
		    gc.add( Calendar.DATE, -5 );
		    break;
		   case ( Calendar.SATURDAY  ):
		    gc.add( Calendar.DATE, -6 );
		    break;
		  }
		  return gc.getTime();
		 }

		
		/**
		  * 取得指定日期的所处星期的最后一天
		  * 
		  * @param date
		  *            指定日期。
		  * @return 指定日期的所处星期的最后一天
		  */
		 public static synchronized java.util.Date getLastDayOfWeek(
		  java.util.Date date )
		 {
		  /**
		   * 详细设计： 
		   * 1.如果date是星期日，则加6天 
		   * 2.如果date是星期一，则加5天 
		   * 3.如果date是星期二，则加4天
		   * 4.如果date是星期三，则加3天 
		   * 5.如果date是星期四，则加2天 
		   * 6.如果date是星期五，则加1天
		   * 7.如果date是星期六，则加0天
		   */
		  GregorianCalendar gc = ( GregorianCalendar ) Calendar.getInstance();
		  gc.setTime( date );
		  switch ( gc.get( Calendar.DAY_OF_WEEK ) )
		  {
		   case ( Calendar.SUNDAY  ):
		    gc.add( Calendar.DATE, 6 );
		    break;
		   case ( Calendar.MONDAY  ):
		    gc.add( Calendar.DATE, 5 );
		    break;
		   case ( Calendar.TUESDAY  ):
		    gc.add( Calendar.DATE, 4 );
		    break;
		   case ( Calendar.WEDNESDAY  ):
		    gc.add( Calendar.DATE, 3 );
		    break;
		   case ( Calendar.THURSDAY  ):
		    gc.add( Calendar.DATE, 2 );
		    break;
		   case ( Calendar.FRIDAY  ):
		    gc.add( Calendar.DATE, 1 );
		    break;
		   case ( Calendar.SATURDAY  ):
		    gc.add( Calendar.DATE, 0 );
		    break;
		  }
		  return gc.getTime();
		 }

		
		/**
		  * 取得指定日期的所处月份的第一天
		  * 
		  * @param date
		  *            指定日期。
		  * @return 指定日期的所处月份的第一天
		  */
		public static synchronized java.util.Date getFirstDayOfMonth( java.util.Date date )
		 {
		  /**
		   * 详细设计： 1.设置为1号
		   */
		  GregorianCalendar gc = ( GregorianCalendar ) Calendar.getInstance();
		  gc.setTime( date );
		  gc.set( Calendar.DAY_OF_MONTH, 1 );
		  return gc.getTime();
		 }
		
		/**
		  * 取得指定日期的所处月份的最后一天
		  * 
		  * @param date
		  *            指定日期。
		  * @return 指定日期的所处月份的最后一天
		  */
		 public static synchronized java.util.Date getLastDayOfMonth(
		  java.util.Date date )
		 {
		  /**
		   * 详细设计： 
		   * 1.如果date在1月，则为31日 
		   * 2.如果date在2月，则为28日 
		   * 3.如果date在3月，则为31日
		   * 4.如果date在4月，则为30日 
		   * 5.如果date在5月，则为31日 
		   * 6.如果date在6月，则为30日
		   * 7.如果date在7月，则为31日 
		   * 8.如果date在8月，则为31日 
		   * 9.如果date在9月，则为30日
		   * 10.如果date在10月，则为31日 
		   * 11.如果date在11月，则为30日 
		   * 12.如果date在12月，则为31日
		   * 1.如果date在闰年的2月，则为29日
		   */
		  GregorianCalendar gc = ( GregorianCalendar ) Calendar.getInstance();
		  gc.setTime( date );
		  switch ( gc.get( Calendar.MONTH ) )
		  {
		   case 0:
		    gc.set( Calendar.DAY_OF_MONTH, 31 );
		    break;
		   case 1:
		    gc.set( Calendar.DAY_OF_MONTH, 28 );
		    break;
		   case 2:
		    gc.set( Calendar.DAY_OF_MONTH, 31 );
		    break;
		   case 3:
		    gc.set( Calendar.DAY_OF_MONTH, 30 );
		    break;
		   case 4:
		    gc.set( Calendar.DAY_OF_MONTH, 31 );
		    break;
		   case 5:
		    gc.set( Calendar.DAY_OF_MONTH, 30 );
		    break;
		   case 6:
		    gc.set( Calendar.DAY_OF_MONTH, 31 );
		    break;
		   case 7:
		    gc.set( Calendar.DAY_OF_MONTH, 31 );
		    break;
		   case 8:
		    gc.set( Calendar.DAY_OF_MONTH, 30 );
		    break;
		   case 9:
		    gc.set( Calendar.DAY_OF_MONTH, 31 );
		    break;
		   case 10:
		    gc.set( Calendar.DAY_OF_MONTH, 30 );
		    break;
		   case 11:
		    gc.set( Calendar.DAY_OF_MONTH, 31 );
		    break;
		  }
		  //检查闰年
		  if ( ( gc.get( Calendar.MONTH ) == Calendar.FEBRUARY )
		   && ( isLeapYear( gc.get( Calendar.YEAR ) ) ) )
		  {
		   gc.set( Calendar.DAY_OF_MONTH, 29 );
		  }
		  return gc.getTime();
		 }
		 
		 
		 public static synchronized boolean isLeapYear()
		 {
		  Calendar cal = Calendar.getInstance();
		  int year = cal.get( Calendar.YEAR );
		  return isLeapYear( year );
		 }

		 public static synchronized boolean isLeapYear( int year )
		 {
		  /**
		   * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		   * 3.能被4整除同时能被100整除则不是闰年
		   */
		  if ( ( year % 400 ) == 0 )
		   return true;
		  else if ( ( year % 4 ) == 0 )
		  {
		   if ( ( year % 100 ) == 0 )
		    return false;
		   else return true;
		  }
		  else return false;
		 }
		  public static String getTodayDate() {
				Calendar calendar = Calendar.getInstance();
				Date date = new Date();
				calendar.setTime(date);
				return Util.formatDate(calendar.getTime(), "yyyy-MM-dd");
				
			}
		     public static String getTodaysDate() {
				Calendar calendar = Calendar.getInstance();
				Date date = new Date();
				calendar.setTime(date);
				return Util.formatDate(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
				
			}
		     public static String getNumberalDate() {
		    	 Calendar calendar = Calendar.getInstance();
		    	 Date date = new Date();
		    	 calendar.setTime(date);
		    	 int random = (int) ((100) * Math.random());
		    	 String datestr = Util.formatDate(calendar.getTime(), "yyMMddHHmmss");
		    	 return datestr + "" + random;
		     }
		     
		     public static String getSysTime(){
		    		Date date = new Date(System.currentTimeMillis());
		    		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		    		int random = (int) ((100) * Math.random());
		    		String time = sdf.format(date);
		    		return time.trim()+random;
		    	}
		     public static String getSys14Time(){
		    	 Date date = new Date(System.currentTimeMillis());
		    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		    	 int random = (int) ((100) * Math.random());
		    	 String time = sdf.format(date);
		    	 return time.trim();
		     }

		     
			public static String getTomorrowDate() {
				Calendar calendar = Calendar.getInstance();
				Date date = new Date();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, 1);
				return Util.formatDate(calendar.getTime(), "yyyy-MM-dd");
				
			}
			
			public static Float getFloatType(Object ob){ 
				Float f =null;
				try {
					f = Float.parseFloat(ob.toString());
					return f;
				} catch (Exception e) {
					return Float.valueOf(0.00f);
				}
			}

			public static Integer getIntegerType(Object ob){
				Integer i =null;
				try {
					i = Integer.parseInt(ob.toString());
					return i;
				} catch (Exception e) {
					return 0;
				}
			}
			public static String getStringType(Object ob){
				if(ob==null)
					return null;
				else
					return "'"+ob.toString()+"'";
			}
			
			/**
			 * 
			 * @param start 起始时间
			 * @param end	结束时间
			 * @param hours	时间间隔，以小时为单位
			 * @return
			 */
			
			public static boolean isTimeOut(Date start, Date end, int hours){
				if(start!=null && end!=null)
				return end.getTime()-start.getTime() >= hours * 3600000;
				return false;
			}

	/**
	  * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	  */
	public static String getNextDay(String nowdate, int delay) {
		  try{
			  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			  String mdate = "";
			  Date d = convertStringToDate(nowdate);
			  long myTime = (d.getTime() / 1000) + delay * 24 * 60 * 60;
			  d.setTime(myTime * 1000);
			  mdate = format.format(d);
			  return mdate;
		  }catch(Exception e){
			  return null;
		  }
	}
	
	/**
	 * 获得日期字符串
	 */
	public static String getWeekStr(String date) {
		Calendar c = Calendar.getInstance();
		c.setTime(convertStringToDate(date));
		int weekDay = c.get(Calendar.DAY_OF_WEEK);
		if (Calendar.MONDAY == weekDay) {
			return "周一";
		} else if (Calendar.TUESDAY == weekDay) {
			return "周二";
		} else if (Calendar.WEDNESDAY == weekDay) {
			return "周三";
		} else if (Calendar.THURSDAY == weekDay) {
			return "周四";
		} else if (Calendar.FRIDAY == weekDay) {
			return "周五";
		} else if (Calendar.SATURDAY == weekDay) {
			return "周六";
		} else {
			return "周日";
		}
	}
	
}

