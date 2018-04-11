package com.pj.web.dao;

import java.util.List;
import java.util.Map;

import com.pj.core.gereric.GenericDao;
import com.pj.web.model.ErrorMsg;

public interface ErrorMsgMapper extends GenericDao<ErrorMsg, Integer>{
	
	List<ErrorMsg> selectByCodes(Map<String, Object> params);
    
}