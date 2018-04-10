package com.pj.action.service;

import java.util.List;

import com.pj.action.model.ReceiptModel;
import com.pj.service.mq.model.SmsContent;

public interface TransmitService {

	/**
	 * 发送短信
	 */
	public void receive(SmsContent smsContent);
	
	/**
	 * 接收短信回执报告
	 */
	public void receipt(Object object) throws Exception;
	
	/**
	 * 接收短信上行报告
	 */
	public void upstream(Object object) throws Exception;
	
	/**
	 * 测试桩
	 */
	public void testFlow(SmsContent smsContent,List<ReceiptModel> sendList);
	
}
