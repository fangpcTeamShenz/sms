package com.pj.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.User;
import com.pj.web.model.UserCompanyInfo;
import com.pj.web.service.UserCompanyInfoService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/companyInfo")
public class UserCompanyInfoController extends BaseController<UserCompanyInfo, Integer> {

	@Resource
	private UserCompanyInfoService userCompanyInfoService;
	
	@Resource
	private UserService userService;
	
	@Override
	protected GenericService<UserCompanyInfo, Integer> getService() {
		return userCompanyInfoService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_COMPANY_INFO;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username","companyName","status","userId"};
	}
	
	@Override
	protected String list(Model model, UserCompanyInfo entity) {
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
	@Override
	protected String add(Model model, UserCompanyInfo entity) {
		//获取所有的短信用户
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null ,null));
		return super.add(model, entity);
	}
	
	@Override
	protected String edit(Model model, @PathVariable("id") Integer id) {
		UserCompanyInfo info = userCompanyInfoService.selectById(id);
		model.addAttribute("item", info);
		//获取所有的短信用户
		model.addAttribute("users", userService.selectById(info.getUserId()));
		return EDIT;
	}
	
	@RequestMapping(value="/admin/audit/{id}")
	protected String audit(Model model, @PathVariable("id") Integer id) {
		UserCompanyInfo info = userCompanyInfoService.selectById(id);
		model.addAttribute("item", info);
		//获取所有的短信用户
		model.addAttribute("user", userService.selectById(info.getUserId()));
		return "admin/" + getModuleName() + "/audit";
	}
	
}
