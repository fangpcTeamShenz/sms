package com.pj.core.enums;

public enum HttpStatusEnums {
	
	CUSTOM_MESSAGE("CUSTOM_MESSAGE","操作失败"),
	
	SUCCESS("SUCCESS", "操作成功"),
	ERROR("ERROR", "操作失败"), 
	NO_RECORD("NO_RECORD", "无记录"),
	LOGIN_ERROR("LOGIN_ERROR", "用户名或密码错误"),
	ERROR_NO_LOGIN("ERROR_NO_LOGIN", "未登陆，或登陆信息已过期，请重新登陆"),
	ERROR_NO_PERMISSION("ERROR_NO_PERMISSION", "对不起，您还未开通此操作权限，请联系管理员"),
	ERROR_PARAS("ERROR_PARAS", "参数校验失败"),
	ERROR_SIGN("ERROR_SIGN", "签名验证失败"),
	ERROR_PHONE_ISUSE("error_phone_isuse", "手机号码已被注册"),
	ERROR_NO_USER("error_no_user", "用户不存在"),
	ERROR_CODE("ERROR_CODE", "验证码错误"),
	CODE_SENDED("CODE_SENDED", "验证码已发送，请查看短信"),
	CODE_INVALID("CODE_INVALID", "验证码已失效"),
	EXCEPTION("EXCEPTION", "系统异常，请稍后再试"), 
	TEMPLATE_EXIST("TEMPLATE_EXIST","模版已存在"), 
	ERROR_USED_PHONE("ERROR_USED_PHONE","该手机号码已被绑定"),
	TOO_MANY_PHONE("TOO_MANY_PHONE","手机号码过多"),
	ERROR_SEND_TIME("ERROR_SEND_TIME","时间格式不对"),
	ERROR_NO_ADDRESS("ERROR_NO_ADDRESS","没有回执地址");
	
	private final String status;
	private final String message;
	
	HttpStatusEnums(String status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public static String getMessage(String status) {
		for (HttpStatusEnums s : HttpStatusEnums.values()) {
			if (status.equals(s.getStatus())) {
				return s.getMessage();
			}
		}
		return null;
	}
}
