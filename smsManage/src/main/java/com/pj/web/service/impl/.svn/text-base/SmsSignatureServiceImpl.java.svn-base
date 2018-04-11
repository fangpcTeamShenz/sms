package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.SmsSignatureMapper;
import com.pj.web.model.SmsSignature;
import com.pj.web.service.SmsSignatureService;

@Service
public class SmsSignatureServiceImpl extends GenericServiceImpl<SmsSignature, Long> implements SmsSignatureService {

    @Resource
    private SmsSignatureMapper smsSignatureMapper;
    
    public GenericDao<SmsSignature, Long> getDao() {
    	return smsSignatureMapper;
    }

}
