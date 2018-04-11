package com.pj.web.controller.client;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.core.entity.Result;
import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.controller.BaseController;
import com.pj.web.model.SmsSignature;
import com.pj.web.model.User;
import com.pj.web.service.SmsSignatureService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/clientSignature")
public class ClientSignatureController extends BaseController<SmsSignature, Long> {

	@Resource
	private SmsSignatureService smsSignatureService;
	
	@Resource
	private UserService userService;
	
	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_CLIENT_SIGNATURE;
	}

	@Override
	protected GenericService<SmsSignature, Long> getService() {
		return smsSignatureService;
	}
	
	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username","signatureType","signContent","status","userId"};
	}
	
	@RequestMapping(value="/client/list")
	protected String list(Model model,  HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SESSION_USER);
		if(user == null) {
			return "/admin/login";
		}
		model.addAttribute("userId", user.getUserId());
		return "client/signature/list";
	}
	
	@RequestMapping(value="/client/page/list")
	@ResponseBody
	protected Page<Map<String, Object>> pageList(HttpServletRequest request) {
		Page<Map<String, Object>> page = getPage(request);
    	Map<String, Object> criteria = getCriteria(request, getPageListCriteria());
    	
    	List<Map<String, Object>> list = getService().selectByPageList(page, criteria);
    	page.setResult(list);
		return super.pageList(request);
	}
	
	@RequestMapping(value="/client/add")
	protected String add(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SESSION_USER);
		if(user == null) {
			return "/admin/login";
		}else {
			user = userService.selectById(user.getUserId());
		}
		model.addAttribute("user", user);
		return "client/signature/add";
	}
	
	@RequestMapping(value = "/client/insert", method = RequestMethod.POST)
	public @ResponseBody Result insert(SmsSignature entity, BindingResult result) {
		return super.insert(entity, result);
	}
	
}
