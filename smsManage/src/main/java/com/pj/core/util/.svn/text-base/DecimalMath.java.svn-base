package com.pj.core.util;

import java.math.BigDecimal;

public class DecimalMath {
	 public static  BigDecimal jia( BigDecimal d1,  BigDecimal d2)
	 {        // 进行加法运算
	          BigDecimal b1 = d1;
	          BigDecimal b2 = d2;
	           BigDecimal  re=b1.add(b2);
	          return round(re,3);
	      }
	     public static  BigDecimal jian( BigDecimal d1,  BigDecimal d2)
	 {        // 进行减法运算
	          BigDecimal b1 =d1;
	          BigDecimal b2 = d2;
	           BigDecimal  re=b1.subtract(b2);
	          return round(re,3);
	         
	      }
	     public static  BigDecimal cheng( BigDecimal d1,  BigDecimal d2)
	 {        // 进行乘法运算
	          BigDecimal b1 = d1;
	          BigDecimal b2 = d2;
	       
	         BigDecimal  re=b1.multiply(b2);
	          return round(re,3);
	      }
	     public static  BigDecimal chu( BigDecimal d1, BigDecimal d2) {// 进行除法运算取3位有效数字
	          BigDecimal b1 = d1;
	          BigDecimal b2 = d2;
	          BigDecimal  re=b1.divide(b2,3);
	          return round(re,3);
	         
	      }
	     public static  BigDecimal round( BigDecimal d, int len) {     // 进行四舍五入操作
	          BigDecimal b1 = d;
	          BigDecimal b2 = new BigDecimal(1);
	         // 任何一个数字除以1都是原数字
	         // ROUND_HALF_UP是BigDecimal的一个常量， 表示进行四舍五入的操作
	         return b1.divide(b2, len,BigDecimal. ROUND_HALF_UP);
	      }
}
