package com.pj.web.controller;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.Permission;
import com.pj.web.model.Role;
import com.pj.web.model.RolePermission;
import com.pj.web.service.PermissionService;
import com.pj.web.service.RoleService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role, Long> {

	@Resource
	private RoleService roleService;

	@Resource
	private PermissionService permissionService;

	@Override
	protected GenericService<Role, Long> getService() {
		return roleService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_ROLE;
	}

	@RequestMapping(value = "/admin/editinfo/{id}")
	@RequiresPermissions(value=AppConstants.MODULE_ROLE+"_edit")
	protected String editinfo(Model model, @PathVariable("id") long id) {
		Role role = getService().selectById(id);

		// 遍历当前角色权限
		List<Permission> adrolepermission = permissionService.selectPermissionsByRoleId(id);
		String adroles = "";
		for (int i = 0; i < adrolepermission.size(); i++) {
			adroles = adroles + "," + adrolepermission.get(i).getId();

		}
		// 遍历所有权限
		List<Permission> permissions = permissionService.selectList(null);

		model.addAttribute("item", role);
		model.addAttribute("adroles", adroles);
		model.addAttribute("permissions", permissions);
		return "admin/role/change";
	}

	@RequestMapping(value = "/self/updateinfo")
	@RequiresPermissions(value = AppConstants.MODULE_ROLE+"_edit")
	protected @ResponseBody Result updateinfo(HttpServletRequest request) {
		long id = Long.parseLong(request.getParameter("id"));
		String[] roles = request.getParameterValues("permission");
		// 删除原有的权限
		permissionService.deletePebyroleId(id);
		// 新增新权限
		if (roles != null) {
			for (int i = 0; i < roles.length; i++) {
				RolePermission role = new RolePermission();
				role.setPermissionId(Long.parseLong(roles[i]));
				role.setRoleId(id);
				permissionService.addrole(role);
			}
		}
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}

	// 跳转到新增页面
	@RequestMapping(value = "/admin/toroleadd")
	@RequiresPermissions(value=AppConstants.MODULE_ROLE+"_edit")
	protected String toroleadd(Model model) {

		// 遍历所有权限
		List<Permission> permissions = permissionService.selectList(null);

		model.addAttribute("permissions", permissions);
		return "admin/role/add";
	}

	// 新增角色
	@RequestMapping(value = "/admin/roleadd")
	@RequiresPermissions(value=AppConstants.MODULE_ROLE+"_insert")
	protected @ResponseBody Result viewaddrole(HttpServletRequest request) {
		String rolename = request.getParameter("roleName");
		String description = request.getParameter("description");
		String[] roles = request.getParameterValues("permission");
		Role adminrole = new Role();
		adminrole.setRoleName(rolename);
		adminrole.setRoleSign(rolename);
		adminrole.setDescription(description);
		roleService.insertrole(roles, adminrole);

		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}

	// 删除角色
	@RequestMapping(value = "/admin/roledelete/{id}")
	@RequiresPermissions(value=AppConstants.MODULE_ROLE+"_delete")
	protected @ResponseBody Result viewdeleterole(Model model, @PathVariable("id") long id) {

		roleService.deleterolebyId(id);

		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}

}
