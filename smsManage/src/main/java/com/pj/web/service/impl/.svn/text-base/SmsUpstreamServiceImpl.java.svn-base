package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.SmsUpstreamMapper;
import com.pj.web.model.SmsUpstream;
import com.pj.web.service.SmsUpstreamService;

@Service
public class SmsUpstreamServiceImpl extends GenericServiceImpl<SmsUpstream, Long> implements SmsUpstreamService {

    @Resource
    private SmsUpstreamMapper smsUpstreamMapper;
    
    public GenericDao<SmsUpstream, Long> getDao() {
    	return smsUpstreamMapper;
    }
}
