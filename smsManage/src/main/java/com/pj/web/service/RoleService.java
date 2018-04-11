package com.pj.web.service;

import com.pj.core.entity.Result;
import com.pj.core.gereric.GenericService;
import com.pj.web.model.Role;

import java.util.List;

public interface RoleService extends GenericService<Role, Long> {
    /**
     * 通过用户id 查询用户 拥有的角色
     * 
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(Long userId);

	Result insertrole(String[] roles, Role adminrole);

	void deleterolebyId(long id);
    
}
