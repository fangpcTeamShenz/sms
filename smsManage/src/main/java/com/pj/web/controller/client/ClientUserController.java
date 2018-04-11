package com.pj.web.controller.client;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericService;
import com.pj.core.util.AppUtils;
import com.pj.core.util.PasswordHash;
import com.pj.web.constant.AppConstants;
import com.pj.web.controller.BaseController;
import com.pj.web.model.User;
import com.pj.web.model.UserBill;
import com.pj.web.service.UserBillService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/clientUser")
public class ClientUserController extends BaseController<User, Long> {

	@Resource
	private UserService userService;
	
	@Resource
	private UserBillService userBillService;
	
	@Override
	protected GenericService<User, Long> getService() {
		return userService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_CLIENT_USER;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username","phone","nickname","linkman","userType"};
	}
	
	@RequestMapping(value = "/client/userInfo")
	protected String userInfo(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SESSION_USER);
		int billSize = 0;
		if(user == null) {
			log.info("session失效");
			return "/admin/login";
		}else {
			user = getService().selectById(user.getUserId());
			UserBill bill = new UserBill();
			bill.setUserId(user.getUserId());
			bill.setFeeType(0);
			List<UserBill> bills = userBillService.selectBySelective(bill, null, null);
			billSize = bills.size();
		}
		model.addAttribute("item", user);
		model.addAttribute("billSize",billSize);
		return "client/user/userInfo";
	}
	
	@RequestMapping(value = "/client/update", method = RequestMethod.POST)
    public @ResponseBody Result update(@Valid User entity , HttpSession session) {
		try {
			if(StringUtils.isNotEmpty(entity.getPassword())) {
				entity.setPassword(PasswordHash.createHash(AppUtils.sha256Hex(entity.getPassword())));
			}
			getService().update(entity);
			//清除缓存
			userService.clearCache();
		} catch (Exception e) {
			log.error(e);
			return getErrorResult("password", "创建用户失败");
		}
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
}
