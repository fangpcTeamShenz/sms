package com.pj.core.model;

import java.util.Map;

public class Email {

    private String to[];
    private String cc[];
    private String subject;
    private String text;
    private String template;
    private Map<String, String> params;
    
	public String[] getTo() {
		return to;
	}
	
	public void setTo(String[] to) {
		this.to = to;
	}
	
	public String[] getCc() {
		return cc;
	}
	
	public void setCc(String[] cc) {
		this.cc = cc;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getTemplate() {
		return template;
	}
	
	public void setTemplate(String template) {
		this.template = template;
	}
	
	public Map<String, String> getParams() {
		return params;
	}
	
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}
