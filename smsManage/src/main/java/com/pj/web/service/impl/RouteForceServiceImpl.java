package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.RouteForceMapper;
import com.pj.web.model.RouteForce;
import com.pj.web.service.RouteForceService;

@Service
public class RouteForceServiceImpl extends GenericServiceImpl<RouteForce, Long> implements RouteForceService {

    @Resource
    private RouteForceMapper routeForceMapper;
    
    public GenericDao<RouteForce, Long> getDao() {
    	return routeForceMapper;
    }
}
