package com.pj.web.interceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class CorsFilter implements Filter {

	//Access-Control-Allow-Origin：允许访问的客户端域名，例如：http://web.xxx.com，若为*，则表示从任意域都能访问，即不做任何限制。
	private String allowOrigin;
	//Access-Control-Allow-Methods：允许访问的方法名，多个方法名用逗号分割，例如：GET,POST,PUT,DELETE,OPTIONS。
	private String allowMethods;
	//Access-Control-Allow-Credentials：是否允许请求带有验证信息，若要获取客户端域下的cookie时，需要将其设置为true
	private String allowCredentials;
	//Access-Control-Allow-Headers：允许服务端访问的客户端请求头，多个请求头用逗号分割，例如：Content-Type。
	private String allowHeaders;
	//Access-Control-Expose-Headers：允许客户端访问的服务端响应头，多个响应头用逗号分割。
	private String exposeHeaders;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		allowOrigin = filterConfig.getInitParameter("allowOrigin");
		allowMethods = filterConfig.getInitParameter("allowMethods");
		allowCredentials = filterConfig.getInitParameter("allowCredentials");
		allowHeaders = filterConfig.getInitParameter("allowHeaders");
		exposeHeaders = filterConfig.getInitParameter("exposeHeaders");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		if (StringUtils.isNotEmpty(allowOrigin)) {
			List<String> allowOriginList = Arrays.asList(allowOrigin.split(","));
			if (CollectionUtils.isNotEmpty(allowOriginList)) {
				String currentOrigin = request.getHeader("Origin");
				if (allowOriginList.contains("*") || allowOriginList.contains(currentOrigin)) {
					response.setHeader("Access-Control-Allow-Origin",currentOrigin);
				}
			}
		}
		if (StringUtils.isNotEmpty(allowMethods)) {
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		if (StringUtils.isNotEmpty(allowCredentials)) {
			response.setHeader("Access-Control-Allow-Credentials",
					allowCredentials);
		}
		if (StringUtils.isNotEmpty(allowHeaders)) {
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		if (StringUtils.isNotEmpty(exposeHeaders)) {
			response.setHeader("Access-Control-Expose-Headers", exposeHeaders);
		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
	}
}
