package com.pj.core.util;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Fangpc
 *
 */
public class SimilarityUtil {
	public static void main(String[] args) {
		// 要比较的两个字符串
		String str1 = "【温馨提示】尊敬的客户，您已成功充值50M流量包，流量当月有效";
		String str2 = "尊敬的客户，您已成功充值${a}流量包，流量当月有效";
		System.out.println(levenshtein(str1, str2));//0.7合格
		System.out.println(compareStrContain(str1, str2));
		
	}
	
	/**
	 * 比较字符串包含关系
	 * 前面包含后面
	 * @return
	 */
	public static boolean compareStrContain(String str1,String str2){
		str1 = SimilarityUtil.getTxtContent(str1);
		str2 = SimilarityUtil.getTxtContent(str2);
		boolean retBoo = false;
		boolean contain = true;
		if(StringUtils.isNotBlank(str1) && StringUtils.isNotBlank(str2)){
			char[] arr1 = str1.toCharArray();
			char[] arr2 = str2.toCharArray();
			for (int i = 0; i < arr2.length; i++) {
				if(contain){
					contain = false;
					for (int j = i; j < arr1.length; j++) {
						if(arr2[i]==arr1[j]){
							contain = true;
							break;
						}
					}
				}else{
					retBoo = false;
					break;
				}
			}
			if(contain)
				retBoo = true;
		}
		return retBoo;
	}

	/**
	 * DNA分析 拼字检查 语音辨识 抄袭侦测
	 * 
	 * @createTime 2012-1-12
	 */
	public static float levenshtein(String str1, String str2) {
		str1 = SimilarityUtil.getTxtContent(str1);
		str2 = SimilarityUtil.getTxtContent(str2);
		// 计算两个字符串的长度。
		int len1 = str1.length();
		int len2 = str2.length();
		// 建立上面说的数组，比字符长度大一个空间
		int[][] dif = new int[len1 + 1][len2 + 1];
		// 赋初值，步骤B。
		for (int a = 0; a <= len1; a++) {
			dif[a][0] = a;
		}
		for (int a = 0; a <= len2; a++) {
			dif[0][a] = a;
		}
		// 计算两个字符是否一样，计算左上的值
		int temp;
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
					temp = 0;
				} else {
					temp = 1;
				}
				// 取三个值中最小的
				dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1, dif[i - 1][j] + 1);
			}
		}
		// 取数组右下角的值，同样不同位置代表不同字符串的比较
		// 计算相似度
		float similarity = 1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());
		return similarity;
	}

	// 得到最小值
	private static int min(int... is) {
		int min = Integer.MAX_VALUE;
		for (int i : is) {
			if (min > i) {
				min = i;
			}
		}
		return min;
	}
	
	/**
	 * 得到去掉花括号和签名的纯文本
	 * 
	 * @param map
	 * @param content
	 * @return
	 */
	public static String getTxtContent(String txtContent) {
		return txtContent.replaceAll("\\$\\{.*?\\}","").replaceAll("\\【.*?\\】","");
	}
	
}
