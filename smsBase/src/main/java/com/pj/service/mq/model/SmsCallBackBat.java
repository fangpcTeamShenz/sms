package com.pj.service.mq.model;

import java.io.Serializable;

/**
 * 短信回执批量处理
 * @author Fangpc
 *
 */
public class SmsCallBackBat implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7656717040949311081L;

	//手机号码
	private String phones;
	//订单号码
	private String transactionCode;
	//用户账户
	private String accountId;
	//对接模块id
	private String moduleId;
	//0:成功,1:失败,200未知
	private String status;
	//附加消息
	private String message;
	//yyyy-MM-dd HH:mm:ss
	private String receiveTime;


	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getPhones() {
		return phones;
	}

	public void setPhones(String phones) {
		this.phones = phones;
	}
	
}

