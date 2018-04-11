package com.pj.web.service.impl;

import com.pj.core.entity.Result;
import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.RoleMapper;
import com.pj.web.dao.RolePermissionMapper;
import com.pj.web.model.Role;
import com.pj.web.model.RolePermission;
import com.pj.web.service.RoleService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends GenericServiceImpl<Role, Long> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public GenericDao<Role, Long> getDao() {
        return roleMapper;
    }

    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return roleMapper.selectRolesByUserId(userId);
    }

	@Override
	public Result insertrole(String[] roles, Role role) {
		// 新增角色
		roleMapper.insertSelective(role);
		// 添加权限

		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setPermissionId(Long.valueOf(roles[i]));
				rolePermission.setRoleId(role.getId());
				rolePermissionMapper.insertSelective(rolePermission);
			}
		}
		return null;
		
	}

	@Override
	public void deleterolebyId(long id) {
		roleMapper.deleteByPrimaryKey(id);
		rolePermissionMapper.deletePebyroleId(id);
	}

}
