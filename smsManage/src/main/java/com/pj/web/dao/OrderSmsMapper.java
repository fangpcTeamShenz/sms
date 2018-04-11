package com.pj.web.dao;

import java.util.List;
import java.util.Map;

import com.pj.core.gereric.GenericDao;
import com.pj.web.model.OrderSms;

public interface OrderSmsMapper extends GenericDao<OrderSms, Long>{
   
	Integer selectSumByYesterday(Map<String,Object> params);
	
	List<Map<String,Object>> selectByPageList(Map<String,Object> param);
	
}