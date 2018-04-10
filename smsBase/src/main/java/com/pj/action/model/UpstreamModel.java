package com.pj.action.model;

public class UpstreamModel {

	//对应的手机号码
	private String mobile;
	
	//同一批任务ID
	private String taskid;
	
	//上行内容
	private String content;
	
	//发送时间
	private String receivetime;
	
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


	public String getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
