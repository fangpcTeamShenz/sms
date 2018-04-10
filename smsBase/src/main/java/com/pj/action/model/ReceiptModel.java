package com.pj.action.model;

public class ReceiptModel {

	//对应的手机号码
	private String mobile;
	
	//同一批任务ID
	private String taskid;
	
	//状态报告----10：发送成功，20：发送失败
	private String status;
	
	//接收时间
	private String receivetime;
	
	//上级网关返回值，不同网关返回值不同，仅作为参考
	private String errorcode;
	
	//在我方系统里面的订单号
	private Long orderSystemUniqueId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTaskid() {
		return taskid;
	}

	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public Long getOrderSystemUniqueId() {
		return orderSystemUniqueId;
	}

	public void setOrderSystemUniqueId(Long orderSystemUniqueId) {
		this.orderSystemUniqueId = orderSystemUniqueId;
	}
	
}
