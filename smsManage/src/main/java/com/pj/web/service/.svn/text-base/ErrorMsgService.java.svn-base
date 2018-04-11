package com.pj.web.service;

import java.util.List;
import java.util.Map;

import com.pj.core.gereric.GenericService;
import com.pj.web.model.ErrorMsg;

public interface ErrorMsgService extends GenericService<ErrorMsg, Integer> {

	List<ErrorMsg> selectByCodes(Map<String, Object> params);
	
	void clearCache();
	
	ErrorMsg selectByErroMsg(ErrorMsg msg) ;
	
}
