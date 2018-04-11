package com.pj.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.User;
import com.pj.web.model.UserBill;
import com.pj.web.service.UserBillService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/userBill")
public class UserBillController extends BaseController<UserBill, Long> {

	@Resource
	private UserBillService userBillService;
	
	@Resource
	private UserService userService;
	
	@Override
	protected GenericService<UserBill, Long> getService() {
		return userBillService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_USERBILL;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username","feeType","userId"};
	}
	
	@Override
	protected String list(Model model, UserBill entity) {
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
	@Override
	protected String add(Model model, UserBill entity) {
		//获取用户信息
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null ,null));
		return super.add(model, entity);
	}
	
	@Override
	protected String edit(Model model, @PathVariable("id") Long id) {
		//获取用户信息
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null ,null));
		return super.edit(model, id);
	}
	
}
