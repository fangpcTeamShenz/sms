package com.pj.action;

import com.pj.action.utils.ConfigureUtils;

/**
 * 执行者基类
 * @author Fangpc
 *
 */
public abstract class ActionBase {
	
	protected static int host = Integer.parseInt(ConfigureUtils.getProperty("servicePort"));
	
//	protected abstract void configure();

}
