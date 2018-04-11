package com.pj.web.service.impl;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.UserRoleMapper;
import com.pj.web.model.UserRole;
import com.pj.web.service.UserRoleService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends GenericServiceImpl<UserRole, Long> implements UserRoleService {

	@Resource
	private UserRoleMapper userRoleMapper;
	
	@Override
	public GenericDao<UserRole, Long> getDao() {
		return userRoleMapper;
	}

}
