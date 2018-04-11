package com.pj.core.util;

import com.pj.core.entity.Email;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/**
 * 邮件发送工具类
 *
 */
public class EmailUtils {
	
	private static JavaMailSender javaMailSender;
	private static FreeMarkerConfigurer freeMarkerConfigurer;
	
	static {
		EmailUtils.javaMailSender = (JavaMailSender)SpringContextUtils.getBean("javaMailSender");
		EmailUtils.freeMarkerConfigurer = (FreeMarkerConfigurer)SpringContextUtils.getBean("freeMarkerConfigurer");
	}
	
	/**
	 * 发送邮件
	 * @param email
	 */
	public static void send(Email email) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
			helper.setFrom("baibao@sohan.hk");
			helper.setSubject("【硕软技术】"+email.getSubject());
			helper.setTo(email.getTo());
			helper.setCc(email.getCc() == null ? new String[]{} : email.getCc());
			helper.setText(EmailUtils.getText(email), true);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
	}
	
	/**
	 * 发送邮件
	 * @param email
	 */
	public static void send(Email email, File file) {
		if (file == null) {
			send(email);
			return;
		}
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom("baibao@sohan.hk");
			helper.setSubject("【硕软技术】"+email.getSubject());
			helper.setTo(email.getTo());
			helper.setCc(email.getCc() == null ? new String[]{} : email.getCc());
			helper.setText(EmailUtils.getText(email), true);
			helper.addAttachment(file.getName(), file);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		javaMailSender.send(message);
	}
	
	/**
	 * 使用模板创建邮件内容
	 */
	private static String getText(Email email) {
		if (email.getTemplate() == null) {
			return email.getText();
		}
		String text = null;
    	try {
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate(email.getTemplate());
			text = FreeMarkerTemplateUtils.processTemplateIntoString(template, email.getParams());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return text;
	}
	
}
