package com.pj.web.service;

import com.pj.core.gereric.GenericService;
import com.pj.web.model.Permission;
import com.pj.web.model.RolePermission;

import java.util.List;

public interface PermissionService extends GenericService<Permission, Long> {

	/**
	 * 通过角色id 查询角色 拥有的权限
	 * 
	 * @param roleId
	 * @return
	 */
	List<Permission> selectPermissionsByRoleId(Long roleId);

	int deletePebyroleId(long roleId);

	int addrole(RolePermission role);

}
