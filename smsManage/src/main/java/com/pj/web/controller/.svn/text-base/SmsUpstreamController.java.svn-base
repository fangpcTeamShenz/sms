package com.pj.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.SmsUpstream;
import com.pj.web.model.User;
import com.pj.web.service.SmsUpstreamService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/smsUpstream")
public class SmsUpstreamController extends BaseController<SmsUpstream, Long> {

	@Resource
	private SmsUpstreamService smsUpstreamService;
	
	@Resource
	private UserService userService;
	
	@Override
	protected GenericService<SmsUpstream, Long> getService() {
		return smsUpstreamService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_SMS_UPSTREAM;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"userId","phone","receiveTimeBegin","receiveTimeEnd","number"};
	}
	
	@Override
	protected String list(Model model, SmsUpstream entity) {
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
}
