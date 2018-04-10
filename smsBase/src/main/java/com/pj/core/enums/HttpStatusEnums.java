package com.pj.core.enums;

public enum HttpStatusEnums {
	SUCCESS(1, "操作成功"),
	ERROR(-9999, "操作失败"), 
	ERROR_NO_PERMISSION(-8798, "对不起，您还未开通此操作权限(请检查签名,模板,白名单)，请联系管理员"),
	ERROR_PARAS(-1872, "参数校验失败"),
	ERROR_SIGN(-1873, "签名验证失败"),
	ERROR_CODE(-1874, "验证码验证失败"),
	NO_ACCOUNT(-1875, "账号不存在"),
	NO_IP_MODELID(-1876, "非法IP或者模板不存在"),
	ERROR_FORMAT(-1877, "请求路径不存在"), 
	ERROR_PARAS_NULL(-1878, "某些值为空"),
	ERROR_BODY_NULL(-1879, "post提交过来的body为空"), 
	ERROR_STORAGE(-1880, "入库失败,请重试"),
	ERROR_PARAS_KEYWORD(-1881, "短信含有敏感字"),
	ERROR_FAST(-1882, "操作过于频繁,请稍后访问"),
	ACCOUNT_IS_LOCK(-1883, "账号已锁"),
	ERROR_INVALID(-1884, "失效的请求"),
	NO_BALANCE(-1885, "账号不存在"),
	ERROR_PARAS_LARGE(-1886, "某些字段值过长");

	private final int statusCode;
	private final String message;
	
	HttpStatusEnums(int state, String message) {
		this.statusCode = state;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}


	public String getMessage() {
		return message;
	}
	
	public static String getMessage(String status) {
		for (HttpStatusEnums s : HttpStatusEnums.values()) {
			if (status.equals(s.getStatusCode())) {
				return s.getMessage();
			}
		}
		return null;
	}
}
