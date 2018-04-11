package com.pj.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericService;
import com.pj.core.util.AppUtils;
import com.pj.core.util.DateUtils;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.Charge;
import com.pj.web.model.User;
import com.pj.web.service.ChargeService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/charge")
public class ChargeController extends BaseController<Charge, Long> {

	@Resource
	private ChargeService chargeService;
	
	@Resource
	private UserService userService;
	
	@Override
	protected GenericService<Charge, Long> getService() {
		return chargeService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_CHARGE;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"userId","username","tradeNumber","status","userId"};
	}
	
	@Override
	protected String list(Model model, Charge entity) {
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
	@Override
	public Result insert(Charge entity, BindingResult result) {
		entity.setTradeNumber(DateUtils.format(new Date(), "yyyyMMddHHmmss") + AppUtils.randomNumber(4));
		chargeService.updateBalance(entity);
		return super.insert(entity, result);
	}
	
	@Override
	public Result update(Charge entity, BindingResult result, HttpServletRequest request) {
		chargeService.updateBalance(entity);
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	@Override
	protected String add(Model model, Charge entity) {
		//获取所有的短信用户
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectListBySelective(user));
		return super.add(model, entity);
	}
	
	@Override
	protected String edit(Model model, @PathVariable("id") Long id) {
		Charge charge = chargeService.selectById(id);
		model.addAttribute("item", charge);
		model.addAttribute("user", userService.selectById(charge.getUserId()));
		return EDIT;
	}
	
}
