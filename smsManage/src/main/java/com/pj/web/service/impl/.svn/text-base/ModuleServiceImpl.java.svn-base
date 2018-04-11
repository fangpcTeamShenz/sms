package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.ModuleMapper;
import com.pj.web.model.Module;
import com.pj.web.service.ModuleService;

@Service
public class ModuleServiceImpl extends GenericServiceImpl<Module, Long> implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;
    
    public GenericDao<Module, Long> getDao() {
    	return moduleMapper;
    }
}
