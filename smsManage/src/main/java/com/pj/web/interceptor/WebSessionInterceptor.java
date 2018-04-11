package com.pj.web.interceptor;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.util.GsonUtils;
import com.pj.core.util.Logs;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * web 无状态协议拦截
 * @author Administrator
 *
 */
public class WebSessionInterceptor implements HandlerInterceptor,HandlerExceptionResolver {
	
	private static Logger log = Logger.getLogger(WebSessionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
//		boolean successRet = false;
//		//web前端放行请求
//		String[] successList = new String[]{"/api/user/login","/api/user/insert"};
//		String uri = request.getRequestURI();
//		for (String success : successList) {
//			if(uri.indexOf(success)>-1){
//				successRet = true;
//				break;
//			}
//		}
//		if(successRet){
//			return true;
//		}else{
//			String userId = request.getParameter("userId");
//			String jsessionId = request.getHeader("JSESSIONID");
//			String sessionId = request.getHeader("SESSIONID");
//			if(StringUtils.isNotBlank(jsessionId) && StringUtils.isNotBlank(userId)){
//				if(jsessionId.equals(MD5Util.MD5Encode(sessionId+userId, null))){
//					return true;
//				}else{
//					Result result = new Result(HttpStatusEnums.ERROR_NO_PERMISSION);
//					GsonUtils.printJson(response, GsonUtils.toJson(result));
//					return false;
//				}
//			}else{
//				Result result = new Result(HttpStatusEnums.ERROR_NO_PERMISSION);
//				GsonUtils.printJson(response, GsonUtils.toJson(result));
//				return false;
//			}
//		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
//		System.out.println("请求中");	
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		System.out.println("请求后");
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, 
			Object handler, Exception ex) {
//		System.out.println("请求后");
//	      String location = "";
//          StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
//          location = stacks[2].getClassName() + "." + stacks[2].getMethodName()
//                  + "(" + stacks[2].getLineNumber() + ")";
		PrintWriter writer = null;
		Logs.error("", ex);
		try {
			request.getSession();
			response.setStatus(200);
			response.setContentType("application/json");
			writer = response.getWriter();
			writer.print(GsonUtils.toJson(new Result(HttpStatusEnums.EXCEPTION)));
		} catch (IOException e) {
			log.error("resolveException()", e);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
		return null;
	}

}
