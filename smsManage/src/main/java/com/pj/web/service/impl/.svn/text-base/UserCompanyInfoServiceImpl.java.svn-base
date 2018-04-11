package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.UserCompanyInfoMapper;
import com.pj.web.model.UserCompanyInfo;
import com.pj.web.service.UserCompanyInfoService;

@Service
public class UserCompanyInfoServiceImpl extends GenericServiceImpl<UserCompanyInfo, Integer> implements UserCompanyInfoService {

    @Resource
    private UserCompanyInfoMapper userCompanyInfoMapper;
    
    public GenericDao<UserCompanyInfo, Integer> getDao() {
    	return userCompanyInfoMapper;
    }
}
