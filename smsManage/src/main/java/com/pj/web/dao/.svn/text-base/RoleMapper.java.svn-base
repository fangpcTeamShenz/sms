package com.pj.web.dao;

import com.pj.core.gereric.GenericDao;
import com.pj.web.model.Role;

import java.util.List;

public interface RoleMapper extends GenericDao<Role, Long> {

	/**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param id
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);
    
}