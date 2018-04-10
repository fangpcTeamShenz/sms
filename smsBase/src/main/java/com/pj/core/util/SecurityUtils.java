package com.pj.core.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 
 * @author Linxs
 *
 */
public final class SecurityUtils {
	
	private static final String ADMIN_ROLE = "ADMIN";
	private static final String USER_ROLE = "USER";
	private static final String MERCHANT_ROLE = "MERCHANT";
	private static final String CARDSMERCHANT_ROLE = "CARDSMERCHANT";
	
	/**
	 * XSS跨站及SQL注入攻击的关键字
	 */
	private static final List<String> insecureKeywords = Arrays.asList(new String[] {"&lt;", "&gt;", "&apos", "&quot", "<", ">", "'", "\"",";", 
			"where", "&&", "!", "\\?", "？" ,"#", "<>", "javascript", "script", "src", "js", "java", "function", "iframe", "and", "exec",
			"insert", "delete", "count", "%", "chr", "mid", "master", "truncate", "declare", "union"});//, "char"
	
//	private static final List<String> insecureKeywords = Arrays.asList(new String[] {"&lt;", "&gt;", "&apos", "&quot", "<", ">", "'", "\\","\"",";", "where", "&&", "[", "]", "{", "}" , "!", "\\?", "？" ,"#", "<>", "javascript", "script", "src", "js", "java", "function", "iframe"});
	
	private static final List<String> regexCharacters = Arrays.asList(new String[]{ "+", "$", "^", "[", "]", "(", ")", "{", "}", "\\"});//"?", "/","*", 
	
	private static final List<String> richInsecureKeywords = Arrays.asList(new String[] {"script", "javascript", "iframe", ".js"});
	
	private static Log log = LogFactory.getLog(SecurityUtils.class);

	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static final boolean checkCaptcha(HttpServletRequest request, HttpServletResponse response) {
		VerifyCodeGenerator verify = VerifyCodeGenerator.getInstance();
		if (!verify.check(request)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * @param user
	 * @param session
	 * @return
	 * @throws Exception
	 */
//	public static final String checkAuthority(TuserEntity user,ShopUserInfo shopUser, HttpSession session,String ip) throws Exception {
//		String server;
//		int PORT;
//		ReadConfig config = new ReadConfig();
//		server = config.getServer();
//		PORT = Integer.parseInt(config.getPort2());
//		String result = new SocketUtil().getSocketMessage(new MakeQueryStr().checkShopAuthority(user.getLogname(), session,ip), server, PORT);
//		
//		if(result.contains("操作成功")) {
//			if(isAdminRole(result)) {
//				session.setAttribute("ROLE", ADMIN_ROLE);
//				return ADMIN_ROLE;
//			} 
//			if(1 == shopUser.getRole()){
//				session.setAttribute("ROLE", MERCHANT_ROLE);
//				return MERCHANT_ROLE;
//			}
//			if(2 == shopUser.getRole()){
//				session.setAttribute("ROLE", CARDSMERCHANT_ROLE);
//				return CARDSMERCHANT_ROLE;
//			}
//			session.setAttribute("ROLE", USER_ROLE);
//			return USER_ROLE;
//		} 
//		
//		log.warn(result);
//		throw new IllegalAccessException("this check authority is illegal!");
//	}
	
	/**
	 * 
	 * @param result
	 * @return
	 */
	private static boolean isAdminRole(String result) {
		return result.substring(result.length() - 1, result.length()).equals("1");
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public static boolean keywordIsClean(String keyword) {
		for(String wran : insecureKeywords) { 
			if(keyword.contains(wran)) {
				return false;
			}
		} 
		
		return true;
	}
	
	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public static boolean keywordRichIsClean(String keyword) {
		for(String wran : richInsecureKeywords) { 
			if(keyword.contains(wran)) {
				return false;
			}
		} 
		
		return true;
	}
	

	/**
	 * 
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String filterXSSHacker(String parameter, HttpServletRequest request) {
		
		for(String xssKeyword : insecureKeywords) {
			if(parameter.contains(xssKeyword)) { 
				log.error("[检测到可能存在XSS攻击] 当前访问IP [" +HttpUtils.getIpAddress(request) + "] 恶意数据 [" + parameter + "]" + "命中的keyword [" + xssKeyword + "]"); 
				parameter = parameter.replaceAll(xssKeyword, "");
			}
		}
		
		for(String regex : regexCharacters){
			if(parameter.contains(regex)){
				parameter = parameter.replaceAll("\\" + regex, "");
			}
		}
		
		return parameter;
		
	}
	
	/**
	 * 
	 * @param richText
	 * @param request
	 * @return
	 */
	public static String filterRichTextXSS(String richText, HttpServletRequest request) {
		for(String xssKeyword : richInsecureKeywords) {
			if(richText.contains(xssKeyword)) { 
				log.error("[检测到可能存在XSS攻击] 当前访问IP [" +HttpUtils.getIpAddress(request) + "] 恶意数据 [" + richText + "]"); 
				richText = richText.replaceAll(xssKeyword, "");
			}
		}
		
		return richText;
	}
	
	public static boolean checkFileType(String fileName, String[] allowFiles) {
		String lowerFileName = fileName.toLowerCase();
		if (lowerFileName.contains(".jsp") || lowerFileName.contains(".asp") || lowerFileName.contains(".php")) {
			return false;
		}
		
		Iterator<String> type = Arrays.asList(allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (lowerFileName.endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
}