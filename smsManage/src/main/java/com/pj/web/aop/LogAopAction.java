package com.pj.web.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * 切点类 2017年4月24日 17:40:42
 * 
 * @author 陈元斌
 */
@Aspect
@Component
public class LogAopAction {
	// 本地异常日志记录对象
	//private static final Logger logger = LoggerFactory.getLogger(LogAopAction.class);

	/*@Resource
	private LogsService logsService;

	*//**
	 * Controller 拦截点,前置通知
	 *//*
	@Pointcut("@annotation(com.pj.web.annotation.SystemLog)")
	public void beforeController() {
	}

	*//**
	 * 操作异常记录
	 * 
	 *//*
	@AfterThrowing(pointcut = "beforeController()", throwing = "e")
	public void doAfterThrowing(JoinPoint point, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Logs logForm = new Logs();
		Map<String, Object> map = null;
		Long userId = null;
		String ip = null;
		try {
			ip = AppUtils.getIpAddress(request);
		} catch (Exception ee) {
			ip = "无法获取登录用户Ip";
		}

		try {
			map = getControllerMethodDescription(point);
			// 登录用户 0L表示无法获取登录用户信息
			User user = (User) request.getSession().getAttribute("SESSION_USER");
			if (user == null) {
				userId = 0L;
			} else {
				userId = user.getId();
			}
		} catch (Exception ee) {
			userId = 0L;
		}

		try {
			logForm.setUrl(AppUtils.getURI(request));
			logForm.setUserId(userId);
			logForm.setTitle(String.valueOf(map.get("module")));
			logForm.setMethods(String.valueOf(map.get("methods")));
			logForm.setMessage(String.valueOf(map.get("description"))+"--执行异常");
			logForm.setClassName(String.valueOf(map.get("className")));
			logForm.setDuration(0L);
			logForm.setIp(ip);
			logForm.setOptime(new Date());
			logForm.setIp(ip);
			logsService.insert(logForm);
		} catch (Exception e1) {
			Log.info(e1);
		}
	}

	*//**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 *            切点
	 *//*
	@Around("beforeController()")
	public Object doController(ProceedingJoinPoint point) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Object result = null;
		Logs logForm = new Logs();
		Map<String, Object> map = null;
		Long userId = null;
		Long start = 0L;
		Long end = 0L;
		Long time = 0L;
		String ip = null;
		try {
			ip = AppUtils.getIpAddress(request);
		} catch (Exception e) {
			ip = "无法获取登录用户Ip";
		}
		try {
			logForm.setParams(getHandleParams(request));
		} catch (Exception e) {
			logger.info("日志记录--请求参数获取异常：" + e);
		}
		
		try {
			User user = (User) request.getSession().getAttribute("SESSION_USER");
			if (user == null) {
				userId = 0L;
			} else {
				userId = user.getId();
			}
		} catch (Exception ee) {
			userId = 0L;// 登录用户 0L表示无法获取登录用户信息
		}
		
		try {
			map = getControllerMethodDescription(point);
			// 执行方法所消耗的时间
			start = System.currentTimeMillis();
			result = point.proceed();
			end = System.currentTimeMillis();
			time = end - start;
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		
		try {
			String url = AppUtils.getURI(request);
			String moule = String.valueOf(map.get("module"));
			if(StringUtils.isBlank(moule)){
				moule = url.substring(1, url.indexOf("/",1));
			}
			
			logForm.setUrl(url);
			logForm.setTitle(moule);
		} catch (Exception e) {
			logger.info("" + e);
		}
		
		// 当前用户
		try {
			logForm.setUserId(userId);
			logForm.setMethods(String.valueOf(map.get("methods")));
			logForm.setMessage(String.valueOf(map.get("description")));
			logForm.setClassName(String.valueOf(map.get("className")));
			logForm.setDuration(time);
			logForm.setIp(ip);
			logForm.setOptime(new Date());
			logForm.setIp(ip);
			logsService.insert(logForm);
		} catch (Throwable e) {
			// 记录本地异常日志
			logger.error("====通知异常====");
			logger.error("异常信息:", e.getMessage());
			throw new RuntimeException(e);
		}
		return result;
	}

	*//**
	 * 获取请求参数
	 * @param request
	 * @return
	 *//*
	private String getHandleParams(HttpServletRequest request) {
		Map<String,Object> params = new HashMap<String, Object>();
		Enumeration<String> paramsNames = request.getParameterNames();
		while (paramsNames.hasMoreElements()) {
			String paraName = (String) paramsNames.nextElement();
			
			String value = request.getParameter(paraName);
    		if (StringUtils.isNotBlank(value)) {
    			try {
    				params.put(paraName.trim(), new String(value.trim().getBytes("ISO-8859-1"), "UTF-8"));
				} catch (Exception e) {
					logger.error("获取请求参数错误 ==>", e);
				}
    		}
    		params.put(paraName, request.getParameter(paraName));
		}
		return GsonUtils.toJson(params);
	}

	*//**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 *//*
	public Map<String, Object> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Signature sign= joinPoint.getSignature();
		String methodName = sign.getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		
		MethodSignature methodSignature = (MethodSignature) sign;
		Method method = methodSignature.getMethod();   
		SystemLog annots = method.getAnnotation(SystemLog.class);
		map.put("module", annots.module());
		map.put("methods", methodName);
		map.put("className", className);
		String de = annots.description();
		if (StringUtils.isEmpty(de)){
			de = "执行成功!";
		}
		map.put("description", de);	
		
		return map;
	}*/
}
