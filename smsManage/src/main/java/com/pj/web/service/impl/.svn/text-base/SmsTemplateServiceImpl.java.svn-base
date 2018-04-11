package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.SmsTemplateMapper;
import com.pj.web.model.SmsTemplate;
import com.pj.web.service.SmsTemplateService;

@Service
public class SmsTemplateServiceImpl extends GenericServiceImpl<SmsTemplate, Long> implements SmsTemplateService {

    @Resource
    private SmsTemplateMapper smsTemplateMapper;
    
    public GenericDao<SmsTemplate, Long> getDao() {
    	return smsTemplateMapper;
    }
}
