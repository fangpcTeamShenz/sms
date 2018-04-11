package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.UserBillMapper;
import com.pj.web.model.UserBill;
import com.pj.web.service.UserBillService;

@Service
public class UserBillServiceImpl extends GenericServiceImpl<UserBill, Long> implements UserBillService {

    @Resource
    private UserBillMapper userBillMapper;
    
    public GenericDao<UserBill, Long> getDao() {
    	return userBillMapper;
    }
}
