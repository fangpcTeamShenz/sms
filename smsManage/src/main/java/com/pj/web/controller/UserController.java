package com.pj.web.controller;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericService;
import com.pj.core.util.AppUtils;
import com.pj.core.util.PasswordHash;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.Role;
import com.pj.web.model.User;
import com.pj.web.model.UserRole;
import com.pj.web.service.RoleService;
import com.pj.web.service.UserRoleService;
import com.pj.web.service.UserService;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User, Long> {

	@Resource
	private UserService userService;
	
	@Resource
	private RoleService roleService;

	@Resource
	private UserRoleService userRoleService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public Result login(User adminUser, HttpSession session,HttpServletRequest request) {
		final User smsUser = userService.selectByNickname(adminUser.getNickname());
		if(smsUser == null){
			return getJSONResult(HttpStatusEnums.LOGIN_ERROR, "账号密码错误！");
		}
		Subject subject = SecurityUtils.getSubject();

		subject.login(new UsernamePasswordToken(smsUser.getUsername(), adminUser.getPassword()));
		
		final User authUserInfo = userService.selectByNickname(adminUser.getNickname());
		session.setAttribute(SESSION_USER, authUserInfo);
		//跳转之前界面
		SavedRequest savedRequest = (SavedRequest) request.getSession().getAttribute(AppConstants.SHIRO_SAVE_REQUEST);
		if(savedRequest != null){
			String recordUrl = savedRequest.getRequestURI();
			String path = request.getContextPath();
			return super.getJSONResult(HttpStatusEnums.SUCCESS, recordUrl.replace(path, ""));
		}
		
		String result = "/admin/index";
		return super.getJSONResult(HttpStatusEnums.SUCCESS, result);
	}

	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(SESSION_USER);
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "admin/login";
	}
	
	@Override
	protected GenericService<User, Long> getService() {
		return userService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_USER;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username", "email", "userType"};
	}

	@Override
	protected Result insertBefore(User entity) {
		if (userService.selectByUsername(entity.getUsername()) != null) {
			return getErrorResult("username", "用户名已存在");
		}
		try {
			entity.setPassword(PasswordHash.createHash(AppUtils.sha256Hex(entity.getPassword())));
			entity.setUserType(1);
			entity.setNickname(entity.getUsername());
		} catch (Exception e) {
			return getErrorResult("password", "创建用户失败");
		}
		return null;
	}
	
	@Override
	protected Result updateBefore(User entity, HttpServletRequest request) {
		if (StringUtils.isBlank(entity.getPassword())) {
			entity.setPassword(null);
		} else {
			try {
				entity.setPassword(PasswordHash.createHash(AppUtils.sha256Hex(entity.getPassword())));
			} catch (Exception e) {
				return getErrorResult("password", "修改密码失败");
			}
		}
		entity.setUsername(null);
		return null;
	}
	
	@RequestMapping(value="/self/edit")
	protected String edit(Model model, HttpSession session) {
		model.addAttribute("item", getService().selectById(getSessionUser(session).getUserId()));
		return "admin/user/change";
	}
	
	@RequestMapping(value = "/self/update", method = RequestMethod.POST)
    public @ResponseBody Result update(@Valid User entity, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return getErrorResult(result);
		}
		
		if (!entity.getUserId().equals(getSessionUser(session).getUserId())) {
			return getErrorResult("username", "用户不存在");
		}
		try {
			entity.setPassword(PasswordHash.createHash(AppUtils.sha256Hex(entity.getPassword())));
		} catch (Exception e) {
			return getErrorResult("password", "创建用户失败");
		}
		getService().update(entity);
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	@RequestMapping(value="/admin/roleedit/{id}")
	@RequiresPermissions(value=AppConstants.MODULE_USER+"_edit")
	protected String editrole(Model model, @PathVariable("id") long id) {
		model.addAttribute("item", getService().selectById(id));
		List<UserRole> userrolelist = userService.selectrole(id);
		long  userrole=0;
		if(userrolelist.size()>0){
			userrole=userrolelist.get(0).getRoleId();
			
		}
		model.addAttribute("userrole", userrole);
		List<Role> adminrole = roleService.selectList(null);
		
		model.addAttribute("roles", adminrole);
		return "admin/user/changerole";
	}
	
	@RequestMapping(value="/admin/roleupdate")
	@RequiresPermissions(value=AppConstants.MODULE_USER+"_edit")
	protected @ResponseBody Result editrole(HttpServletRequest request) {
		long id=Long.parseLong(request.getParameter("userId"));
		long roleid=Long.parseLong(request.getParameter("roleid"));
		userService.updaterole(id,roleid);
		 return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	/**
	 * 短信须知
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/smsNotice")
	protected String smsNotice(Model model, HttpSession session) {
		return "admin/user/smsNotice";
	}

}
