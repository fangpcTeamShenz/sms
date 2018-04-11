package com.pj.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.ErrorMsgMapper;
import com.pj.web.model.ErrorMsg;
import com.pj.web.service.ErrorMsgService;

@Service
public class ErrorMsgServiceImpl extends GenericServiceImpl<ErrorMsg, Integer> implements ErrorMsgService {

    @Resource
    private ErrorMsgMapper errorMsgMapper;
    
    public GenericDao<ErrorMsg, Integer> getDao() {
    	return errorMsgMapper;
    }

	@Override
	public List<ErrorMsg> selectByCodes(Map<String, Object> params) {
		return errorMsgMapper.selectByCodes(params);
	}
	
	@Cacheable(value="errorMsg", key="#errorMsg.errorMsgCode")
	public ErrorMsg selectByErroMsg(ErrorMsg msg) {
		return errorMsgMapper.selectByPrimaryKey(msg.getErrorMsgCode());
	}

	@Override
	@CacheEvict(value={"errorMsg"}, allEntries=true)
	public void clearCache() {
		log.info("清空错误状态缓存成功");
	}
}
