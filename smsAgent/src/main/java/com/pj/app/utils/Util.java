package com.pj.app.utils;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Util
{
  private static Log _$5 = LogFactory.getLog(Util.class);
  static SimpleDateFormat _$4 = new SimpleDateFormat("yyyy-MM-dd");
  static final int _$3 = 160;
  static final int[] _$2 = { 1601, 1637, 1833, 2078, 2274, 2302, 2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027, 4086, 4390, 4558, 4684, 4925, 5249, 5600 };
  static final char[] _$1 = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z' };

  public static String encrypt(String paramString)
  {
    String str = null;
    if (paramString == null)
      return null;
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramString.getBytes());
      BigInteger localBigInteger = new BigInteger(1, localMessageDigest.digest());
      str = localBigInteger.toString(16).toUpperCase();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str;
  }

  public static String filterHtml(String paramString)
  {
    if (paramString == null)
      return null;
    char[] arrayOfChar = new char[paramString.length()];
    paramString.getChars(0, paramString.length(), arrayOfChar, 0);
    StringBuffer localStringBuffer = new StringBuffer(arrayOfChar.length + 50);
    for (int i = 0; i < arrayOfChar.length; i++)
      switch (arrayOfChar[i])
      {
      case '<':
        localStringBuffer.append("&lt;");
        break;
      case '>':
        localStringBuffer.append("&gt;");
        break;
      case '&':
        localStringBuffer.append("&amp;");
        break;
      case '"':
        localStringBuffer.append("&quot;");
        break;
      case '\'':
        localStringBuffer.append("&#39;");
        break;
      default:
        localStringBuffer.append(arrayOfChar[i]);
      }
    return localStringBuffer.toString();
  }

  public static Timestamp getCurrentTime()
  {
    return new Timestamp(System.currentTimeMillis());
  }

/*  public static String[] splitString(String paramString1, String paramString2)
  {
    int i = 0;
    int j = 10;
    int k = 0;
    int m = 0;
    Object localObject = new String[10];
    while (true)
    {
      m = paramString1.indexOf(paramString2, k);
      if (m == -1)
        break;
      if (i == j)
      {
        j += 10;
        arrayOfString = new String[j];
        System.arraycopy(localObject, 0, arrayOfString, 0, i);
        localObject = arrayOfString;
      }
      localObject[i] = paramString1.substring(k, m);
      i++;
      k = m + paramString2.length();
    }
    String[] arrayOfString = new String[i + 1];
    arrayOfString[i] = paramString1.substring(k);
    System.arraycopy(localObject, 0, arrayOfString, 0, i);
    return arrayOfString;
  }*/

  public static Date parseDate(String paramString)
  {
    return parseDate(paramString, "yyyy-MM-dd");
  }

  public static Date parseDate(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString1.equals("")))
      return null;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString2);
    Date localDate = null;
    try
    {
      localDate = localSimpleDateFormat.parse(paramString1);
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return localDate;
  }

  public static String formatDate(Date paramDate)
  {
    return formatDate(paramDate, "yyyy-MM-dd");
  }

  public static String formatDate(Date paramDate, String paramString)
  {
    if (paramDate == null)
      return "";
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(paramString);
    return localSimpleDateFormat.format(paramDate);
  }

  public static Date amountDay(Date paramDate, int paramInt)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.add(5, paramInt);
    return localCalendar.getTime();
  }

  public static Date cleanTimeFields(Date paramDate)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(paramDate);
    localCalendar.set(11, 0);
    localCalendar.set(12, 0);
    localCalendar.set(13, 0);
    localCalendar.set(14, 0);
    return localCalendar.getTime();
  }

  public static String nullToString(String paramString)
  {
    return paramString == null ? "" : paramString;
  }

  public static String getString(Object paramObject, String paramString)
  {
    if (null == paramObject)
      return paramString;
    String str = (String)paramObject;
    return "".equals(str) ? paramString : str;
  }

  public static String replace(String paramString1, String paramString2, String paramString3)
  {
    int i = 0;
    int j = 0;
    StringBuffer localStringBuffer = new StringBuffer();
    while ((j = paramString1.indexOf(paramString2, i)) >= 0)
    {
      localStringBuffer.append(paramString1.substring(i, j));
      localStringBuffer.append(paramString3);
      i = j + paramString2.length();
    }
    localStringBuffer.append(paramString1.substring(i));
    return localStringBuffer.toString();
  }

  public static String split2MultiLine(String paramString, int paramInt)
  {
    int i = paramString.length();
    if (i <= paramInt)
      return paramString;
    StringBuffer localStringBuffer = new StringBuffer();
    int j = 0;
    for (int k = 1; ; k++)
    {
      if (k * paramInt > i)
      {
        localStringBuffer.append(paramString.substring(j));
        break;
      }
      localStringBuffer.append(paramString.substring(j, k * paramInt));
      localStringBuffer.append("��");
      j = k * paramInt;
    }
    return localStringBuffer.toString();
  }

  public static String truncTableField(String paramString)
  {
    return paramString.substring(paramString.indexOf('.') + 1, paramString.length());
  }

  public static String getGBKString(String paramString)
  {
    try
    {
      return System.getProperty("user.language").equalsIgnoreCase("zh") ? paramString : new String(paramString.getBytes("ISO-8859-1"), "GBK");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString;
  }

  public static String getISO8859String(String paramString)
  {
    try
    {
      return System.getProperty("user.language").equalsIgnoreCase("zh") ? paramString : new String(paramString.getBytes("GBK"), "ISO-8859-1");
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString;
  }

  public static String toEncoded(String paramString, HttpServletRequest paramHttpServletRequest)
  {
    String str1 = paramHttpServletRequest.getSession().getId();
    if ((paramString == null) || (str1 == null))
      return paramString;
    str1 = "0000" + str1;
    String str2 = paramString;
    String str3 = "";
    String str4 = "";
    int i = paramString.indexOf('?');
    if (i >= 0)
    {
      str2 = paramString.substring(0, i);
      str3 = paramString.substring(i);
    }
    int j = str2.indexOf('#');
    if (j >= 0)
    {
      str4 = str2.substring(j);
      str2 = str2.substring(0, j);
    }
    StringBuffer localStringBuffer = new StringBuffer(str2);
    if (localStringBuffer.length() > 0)
    {
      localStringBuffer.append(";jsessionid=");
      localStringBuffer.append(str1);
    }
    localStringBuffer.append(str4);
    localStringBuffer.append(str3);
    return localStringBuffer.toString();
  }

  public static boolean checkVerifyCode(HttpServletRequest paramHttpServletRequest, String paramString1, String paramString2)
  {
    String str = getVerifyCode(paramHttpServletRequest, paramString1);
    return str.equals(paramString2);
  }

  public static String getVerifyCode(HttpServletRequest paramHttpServletRequest, String paramString)
  {
    String str = "";
    HttpSession localHttpSession = paramHttpServletRequest.getSession(true);
    if (localHttpSession != null)
    {
      str = localHttpSession.getId();
      str = str + paramString;
      str = str + localHttpSession.getCreationTime();
    }
    else
    {
      str = paramString;
    }
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(str.getBytes());
      BigInteger localBigInteger = new BigInteger(1, localMessageDigest.digest());
      str = localBigInteger.toString(16).toUpperCase();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return str;
  }

  public static boolean copyFile(String paramString1, String paramString2)
  {
    boolean bool = false;
    File localFile1 = new File(paramString1);
    if (!localFile1.isFile())
    {
      _$5.debug("copyFile error: the src file don't exist");
      return bool;
    }
    paramString2 = paramString2.replace(File.separator.charAt(0), '/');
    int i = paramString2.lastIndexOf("/");
    String str = paramString2.substring(0, i);
    localFile1 = new File(str);
    if (!localFile1.exists())
      localFile1.mkdirs();
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(paramString1);
      FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
      File localFile2 = new File(paramString1);
      byte[] arrayOfByte = new byte[(int)localFile2.length()];
      if (localFileInputStream.read(arrayOfByte) != -1)
        localFileOutputStream.write(arrayOfByte);
      localFileOutputStream.close();
      localFileInputStream.close();
      bool = true;
    }
    catch (Exception localException)
    {
      _$5.error("copyFile error:" + localException, localException);
    }
    return bool;
  }

  public static boolean copyDir(String paramString1, String paramString2)
  {
    boolean bool = false;
    File localFile = new File(paramString1);
    if (!localFile.isDirectory())
      return bool;
    localFile = new File(paramString2);
    if (!localFile.exists())
      localFile.mkdirs();
    File[] arrayOfFile = new File(paramString1).listFiles();
    for (int i = 0; i < arrayOfFile.length; i++)
      if (arrayOfFile[i].isFile())
        copyFile(paramString1 + "/" + arrayOfFile[i].getName(), paramString2 + "/" + arrayOfFile[i].getName());
    bool = true;
    return bool;
  }

  public static boolean moveFile(String paramString1, String paramString2)
  {
    boolean bool = false;
    if (copyFile(paramString1, paramString2))
      try
      {
        File localFile = new File(paramString1);
        localFile.delete();
        bool = true;
      }
      catch (Exception localException)
      {
        _$5.error("moveFile error: delete fail," + localException, localException);
      }
    return bool;
  }

  public static boolean deleteFile(String paramString)
  {
    boolean bool = false;
    try
    {
      File localFile = new File(paramString);
      localFile.delete();
      bool = true;
    }
    catch (Exception localException)
    {
      _$5.error("deleteFile error: " + localException, localException);
    }
    return bool;
  }


  public static int getPageRows(HttpServletRequest paramHttpServletRequest, int paramInt)
  {
    String str = paramHttpServletRequest.getParameter("pageRows");
    if ((null == str) || (str.equals("")))
    {
      str = (String)paramHttpServletRequest.getSession().getAttribute("pageRows");
      if ((null == str) || (str.equals("")))
        str = String.valueOf(paramInt);
    }
    paramHttpServletRequest.getSession().setAttribute("pageRows", str);
    int i = paramInt;
    try
    {
      i = Integer.parseInt(str);
    }
    catch (Exception localException)
    {
      _$5.error("Util.getPageRows() parseInt(" + str + ")");
    }
    return i;
  }

/*  public static String getGBKInitial(String paramString)
  {
    paramString = paramString.toLowerCase();
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      for (int j = 0; j < paramString.length(); j++)
      {
        int i = paramString.charAt(j);
        char[] arrayOfChar =  {i} ;
        byte[] arrayOfByte = new String(arrayOfChar).getBytes("GBK");
        if ((arrayOfByte[0] < 128) && (arrayOfByte[0] > 0))
          localStringBuffer.append(arrayOfChar);
        else
          localStringBuffer.append(_$1(arrayOfByte));
      }
    }
    catch (Exception localException)
    {
      _$5.error("getGBKInitial " + paramString + " error:" + localException);
    }
    return localStringBuffer.toString();
  }
*/
  private static char _$1(byte[] paramArrayOfByte)
  {
    char c = '-';
    int i = 0;
    for (int j = 0; j < paramArrayOfByte.length; j++)
    {
      int tmp15_14 = j;
      paramArrayOfByte[tmp15_14] = (byte)(paramArrayOfByte[tmp15_14] - 160);
    }
    i = paramArrayOfByte[0] * 100 + paramArrayOfByte[1];
    for (int j = 0; j < 23; j++)
      if ((i >= _$2[j]) && (i < _$2[(j + 1)]))
      {
        c = _$1[j];
        break;
      }
    return c;
  }

  public static void zipFile(String paramString1, String paramString2)
  {
    try
    {
      paramString2 = paramString2.replace(File.separator.charAt(0), '/');
      int i = paramString2.lastIndexOf("/");
      String str = paramString2.substring(0, i);
      File localFile1 = new File(str);
      if (!localFile1.exists())
        localFile1.mkdirs();
      File localFile2 = new File(paramString1);
      FileInputStream localFileInputStream = new FileInputStream(localFile2);
      ZipOutputStream localZipOutputStream = new ZipOutputStream(new FileOutputStream(paramString2));
      localZipOutputStream.putNextEntry(new ZipEntry(localFile2.getName()));
      int j = 0;
      byte[] arrayOfByte = new byte[8192];
      while ((j = localFileInputStream.read(arrayOfByte, 0, 8192)) != -1)
        localZipOutputStream.write(arrayOfByte, 0, j);
      localFileInputStream.close();
      localZipOutputStream.close();
    }
    catch (Exception localException)
    {
      _$5.error("zipFile error:" + localException, localException);
    }
  }

  public static void unzipFile(String paramString1, String paramString2)
  {
    try
    {
      ZipInputStream localZipInputStream = new ZipInputStream(new FileInputStream(paramString1));
      ZipEntry localZipEntry = null;
      while ((localZipEntry = localZipInputStream.getNextEntry()) != null)
        if (false == localZipEntry.isDirectory())
        {
          paramString2 = paramString2.replace(File.separator.charAt(0), '/');
          int i = paramString2.lastIndexOf("/");
          String str = paramString2.substring(0, i);
          File localFile = new File(str);
          if (!localFile.exists())
            localFile.mkdirs();
          FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
          int j = 0;
          byte[] arrayOfByte = new byte[8192];
          while ((j = localZipInputStream.read(arrayOfByte, 0, 8192)) != -1)
            localFileOutputStream.write(arrayOfByte, 0, j);
          localFileOutputStream.close();
        }
      localZipInputStream.close();
    }
    catch (Exception localException)
    {
      _$5.error("unzipFile error:" + localException, localException);
    }
  }

  public static boolean hasFile(String paramString)
  {
    File localFile = new File(paramString);
    return localFile.exists();
  }

  public static BufferedReader readFile(String paramString)
  {
    File localFile = new File(paramString);
    BufferedReader localBufferedReader = null;
    try
    {
      localBufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(localFile)));
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    return localBufferedReader;
  }

  public static void main(String[] paramArrayOfString)
  {
  }
}