package com.pj.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericService;
import com.pj.core.util.AppUtils;
import com.pj.core.util.DateUtils;
import com.pj.core.util.MD5Util;
import com.pj.core.util.PasswordHash;
import com.pj.web.constant.AppConstants;
import com.pj.web.init.StartInit;
import com.pj.web.model.Charge;
import com.pj.web.model.User;
import com.pj.web.model.UserRole;
import com.pj.web.service.ChargeService;
import com.pj.web.service.OrderSmsService;
import com.pj.web.service.UserRoleService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/smsUser")
public class SmsUserController extends BaseController<User, Long> {

	@Resource
	private UserService userService;
	
	@Resource
	private OrderSmsService orderSmsService;
	
	@Resource
	private UserRoleService userRoleService;
	
	@Resource
	private ChargeService chargeService;
	
	@Resource(name="startInit")
	private StartInit startInit;
	
	@Override
	protected GenericService<User, Long> getService() {
		return userService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_SMS_USER;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username","phone","nickname","linkman","userType"};
	}
	
	@RequestMapping("/admin/showList")
	protected @ResponseBody Page<Map<String, Object>> showProjects(HttpServletRequest request){
		
		Page<Map<String, Object>> page = getPage(request);
    	Map<String, Object> criteria = getCriteria(request, getPageListCriteria());
    	
    	List<Map<String, Object>> list = userService.selectSmsUserByPageList(page, criteria);
    	page.setResult(list);
		
		return page;
	}
	
	@Override
	public Result insert(User entity, BindingResult result) {
		try {
			String pwd = "123456";//AppUtils.randomStr(6);
			entity.setUsername(MD5Util.MD5Encode(AppUtils.randomStr(11), "utf-8"));
			entity.setPassword(PasswordHash.createHash(AppUtils.sha256Hex(pwd)));
			entity.setAppsecret(AppUtils.randomStr(32));
			entity.setBalance(10L);
			getService().insert(entity);
			//生成充值记录
			Charge charge = new Charge();
			charge.setTradeNumber(DateUtils.format(new Date(), "yyyyMMddHHmmss") + AppUtils.randomNumber(4));
			charge.setUserId(entity.getUserId());
			charge.setGiftAmount(10L);
			charge.setStatus(2);
			charge.setMemo("系统赠送");
			chargeService.insert(charge);
			//绑定角色
			UserRole uRole = new UserRole();
			uRole.setRoleId(Long.valueOf("8"));
			uRole.setUserId(entity.getUserId());
			userRoleService.insert(uRole);
//			//发送固定模板短信通知  短信内容需要替换
//			orderSmsService.systemSendSms(entity.getPhone(), 
//					"【沛璟】尊敬的用户您好，您当前在进行重置密码操作，重置后的密码为"+pwd+"，请不要告诉他人！");
			//清除缓存
			userService.clearCache();
			//重置redis
			startInit.loadUserAccount();
		} catch (Exception e) {
			log.error(e);
		}
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	@Override
	public Result update(User entity, BindingResult result, HttpServletRequest request) {
		getService().update(entity);
		//清除缓存
		userService.clearCache();
		//重置redis
		startInit.loadUserAccount();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	/**
	 * 敏感字
	 */
	@RequestMapping(value = "/admin/editKeyword/{id}")
	protected String editKeyword(Model model, @PathVariable("id") Long id) {
		model.addAttribute("item", userService.selectById(id));
		return "admin/" + getModuleName() + "/keyword";
	}
	
	/**
	 * 敏感字白名单
	 */
	@RequestMapping(value = "/admin/updateKeyWord", method = RequestMethod.POST)
	public @ResponseBody Result updateKeyWord(User entity) {
		getService().update(entity);
		//重置redis
		startInit.loadUserAccount();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	/**
	 * 去重置密码页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gotoPwdReset")
	protected String gotoPwdReset(Model model) {
		return "admin/" + getModuleName() + "/reset";
	}
	
	/**
	 * 重置密码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/pwdReset", method = RequestMethod.POST)
	public @ResponseBody Result pwdReset(HttpServletRequest request) {
		String username = request.getParameter("username");
		if(StringUtils.isEmpty(username)){
			return getJSONResult(HttpStatusEnums.ERROR_PARAS, "登录账号不能为空！");
		}
		try {
			User user = userService.selectByUsername(username);
			if(user == null){
				return getJSONResult(HttpStatusEnums.ERROR, "该账户不存在！");
			}
			String pwd = AppUtils.randomStr(6);
			user.setPassword(PasswordHash.createHash(AppUtils.sha256Hex(pwd)));
			userService.update(user);
			//发送短信通知
			orderSmsService.systemSendSms(user.getPhone(), "【沛璟】尊敬的用户您好，您当前在进行重置密码操作，重置后的密码为"+pwd+"，请不要告诉他人！");
			
		} catch (Exception e) {
			return getJSONResult(HttpStatusEnums.EXCEPTION, "网络异常，请稍后再试！");
		}
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	/**
	 * 去注册页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/gotoRegister")
	protected String gotoRegister(Model model) {
		return "admin/" + getModuleName() + "/register";
	}
	
	/**
	 * 新用户注册
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody Result register(HttpServletRequest request, User user) {
		try {
			User smsUser = userService.selectByNickname(user.getNickname());
			if(smsUser != null){
				return getJSONResult(HttpStatusEnums.ERROR, "该用户名已被使用！");
			}
			user.setUserType(0);
			user.setBalance(10L);
			user.setUsername(MD5Util.MD5Encode(AppUtils.randomStr(11), "utf-8"));
			user.setAppsecret(AppUtils.randomStr(32));
			user.setPassword(PasswordHash.createHash(AppUtils.sha256Hex(user.getPassword())));
			userService.insert(user);
			
			//生成充值记录
			Charge charge = new Charge();
			charge.setTradeNumber(DateUtils.format(new Date(), "yyyyMMddHHmmss") + AppUtils.randomNumber(4));
			charge.setUserId(user.getUserId());
			charge.setGiftAmount(10L);
			charge.setStatus(2);
			charge.setMemo("系统赠送");
			chargeService.insert(charge);
			//绑定角色
			UserRole uRole = new UserRole();
			uRole.setRoleId(Long.valueOf("8"));
			uRole.setUserId(user.getUserId());
			userRoleService.insert(uRole);
			//清除缓存
			userService.clearCache();
			//写入到redis中
			startInit.loadUserAccount();
		} catch (Exception e) {
			return getJSONResult(HttpStatusEnums.EXCEPTION, "网络异常，请稍后再试！");
		}
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
}
