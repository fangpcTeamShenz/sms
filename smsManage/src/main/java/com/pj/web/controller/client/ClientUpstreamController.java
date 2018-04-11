package com.pj.web.controller.client;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.controller.BaseController;
import com.pj.web.model.SmsUpstream;
import com.pj.web.model.User;
import com.pj.web.service.SmsUpstreamService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/clientUpstream")
public class ClientUpstreamController extends BaseController<SmsUpstream, Long> {

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
	
	@RequestMapping(value="/client/list")
	protected String list(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SESSION_USER);
		if(user == null) {
			return "/admin/login";
		}
		model.addAttribute("userId", user.getUserId());
		return "client/upstream/list";
	}
	
	@RequestMapping(value="/client/page/list")
	@ResponseBody
	protected Page<Map<String, Object>> pageList(HttpServletRequest request) {
		Page<Map<String, Object>> page = getPage(request);
    	Map<String, Object> criteria = getCriteria(request, getPageListCriteria());
    	
    	List<Map<String, Object>> list = getService().selectByPageList(page, criteria);
    	page.setResult(list);
    	return page;
	}
	
}
