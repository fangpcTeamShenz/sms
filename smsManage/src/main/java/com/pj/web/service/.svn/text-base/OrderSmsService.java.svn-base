package com.pj.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.pj.core.entity.Result;
import com.pj.core.gereric.GenericService;
import com.pj.web.model.OrderSms;

public interface OrderSmsService extends GenericService<OrderSms, Long> {
	
	/**
	 * 发送短信信息
	 * @param request
	 * @param seesion
	 * @return
	 */
	Result sendSmsContent(HttpServletRequest request, HttpSession session);
	
	/**
	 * 解析导入的号码
	 * @param uploadExcelFile, suffix
	 * @return
	 */
	String analyzeImportPhones(MultipartFile uploadExcelFile, String suffix);
	
	/**
	 * 统计昨天账单
	 * @param params
	 * @return
	 */
	Integer selectSumByYesterday(Map<String,Object> params);
	
	/**
	 * 系统发送短信
	 * @param phone
	 * @param smsContent
	 * @return
	 */
	boolean systemSendSms(String phone, String smsContent);
	
	List<Map<String,Object>> selectByPageList(Map<String,Object> param);
	
	/**
	 * 手动回调
	 * @param orders
	 * @return
	 */
	boolean manualCallback(Map<String, Object> param);
	
}
