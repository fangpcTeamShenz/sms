package com.pj.web.dao;

import com.pj.core.gereric.GenericDao;
import com.pj.web.model.Permission;

import java.util.List;

public interface PermissionMapper extends GenericDao<Permission, Long> {

	/**
     * 通过角色id 查询角色 拥有的权限
     * 
     * @param roleId
     * @return
     */
    List<Permission> selectPermissionsByRoleId(Long roleId);
    
}