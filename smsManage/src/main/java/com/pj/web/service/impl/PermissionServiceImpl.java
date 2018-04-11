package com.pj.web.service.impl;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.PermissionMapper;
import com.pj.web.dao.RolePermissionMapper;
import com.pj.web.model.Permission;
import com.pj.web.model.RolePermission;
import com.pj.web.service.PermissionService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends GenericServiceImpl<Permission, Long> implements PermissionService {

	@Resource
	private PermissionMapper permissionMapper;

	@Resource
	private RolePermissionMapper rolePermissionMapper;

	@Override
	public GenericDao<Permission, Long> getDao() {
		return permissionMapper;
	}

	@Override
	public List<Permission> selectPermissionsByRoleId(Long roleId) {
		return permissionMapper.selectPermissionsByRoleId(roleId);
	}

	@Override
	public int deletePebyroleId(long roleId) {

		return rolePermissionMapper.deletePebyroleId(roleId);
	}

	@Override
	public int addrole(RolePermission role) {

		return rolePermissionMapper.insertSelective(role);
	}

}
