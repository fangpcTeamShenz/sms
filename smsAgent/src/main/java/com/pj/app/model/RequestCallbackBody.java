package com.pj.app.model;

import java.io.Serializable;

public class RequestCallbackBody implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2815711449738591038L;

	private String transactionCode;
	
	private String moduleId;
	
	private String status;
	
	private String message;
	
	private String accountId;

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
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
	
	

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "RequestCallbackBody [transactionCode=" + transactionCode
				+ ", moduleId=" + moduleId + ", status=" + status
				+ ", message=" + message + "]";
	}

	
}
