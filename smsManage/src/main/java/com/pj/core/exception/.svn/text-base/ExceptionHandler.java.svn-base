package com.pj.core.exception;

import com.pj.core.entity.ErrorResult;
import com.pj.core.enums.HttpStatusEnums;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	protected static final Logger log = Logger.getLogger(ExceptionHandler.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		if (isAjax(request)) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				response.setContentType("application/json;charset=utf-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter printWriter = response.getWriter();
				if (ex instanceof AuthenticationException) {
					ErrorResult errorResult = new ErrorResult(HttpStatusEnums.ERROR_NO_LOGIN);
					errorResult.getErrors().put("username", ex.getMessage());
					String result = mapper.writeValueAsString(errorResult);
					printWriter.print(result);
				} else if (ex instanceof AuthorizationException) {
					ErrorResult errorResult = new ErrorResult(HttpStatusEnums.ERROR_NO_PERMISSION);
					errorResult.getErrors().put("username", ex.getMessage());
					String result = mapper.writeValueAsString(errorResult);
					printWriter.print(result);
				} else if (ex instanceof CustomException){
					ErrorResult errorResult = new ErrorResult(HttpStatusEnums.getMessage(ex.getMessage()));
					errorResult.getErrors().put("", ex.getMessage());
					String result = mapper.writeValueAsString(errorResult);
					printWriter.print(result);
				} else {
					String result = mapper.writeValueAsString(new ErrorResult(HttpStatusEnums.EXCEPTION));
					printWriter.print(result);
				}
				printWriter.flush();
				printWriter.close();
			} catch (IOException e) {
				return null;
			}
		} else {
			if (ex instanceof AuthenticationException) {
				return new ModelAndView("admin/login");
			} else if (ex instanceof AuthorizationException) {
				return new ModelAndView("401");
			} else {
				return new ModelAndView("500");
			}
		}
		return null;
	}
	
	private boolean isAjax(HttpServletRequest request) {
		return request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
	}

}
