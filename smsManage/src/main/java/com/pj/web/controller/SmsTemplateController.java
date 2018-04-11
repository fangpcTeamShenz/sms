package com.pj.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericService;
import com.pj.web.annotation.SystemLog;
import com.pj.web.constant.AppConstants;
import com.pj.web.init.StartInit;
import com.pj.web.model.SmsTemplate;
import com.pj.web.model.User;
import com.pj.web.service.SmsTemplateService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/smsTemplate")
public class SmsTemplateController extends BaseController<SmsTemplate, Long> {

	@Resource
	private SmsTemplateService smsTemplateService;
	
	@Resource
	private UserService userService;
	
	@Resource(name="startInit")
	private StartInit startInit;
	
	@Override
	protected GenericService<SmsTemplate, Long> getService() {
		return smsTemplateService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_TEMPLATE;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username","templateName","templateContent","productType","status","userId"};
	}
	
	@Override
	protected String list(Model model, SmsTemplate entity) {
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
	@Override
	protected String add(Model model, SmsTemplate entity) {
		//获取所有的短信用户
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null ,null));
		return super.add(model, entity);
	}
	
	@Override
	protected String edit(Model model, @PathVariable("id") Long id) {
		SmsTemplate template = smsTemplateService.selectById(id);
		model.addAttribute("item", template);
		model.addAttribute("user", userService.selectById(template.getUserId()));
		return EDIT;
	}
	
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@SystemLog(description = "修改")
    public @ResponseBody Result update(SmsTemplate entity) {
		checkPermission(getModuleName()+"_edit");
		getService().update(entity);
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	/**
	 * 软删除
	 */
	@RequestMapping(value = "admin/delete/{id}")
	public @ResponseBody
	Result delete(@PathVariable("id") Long id) {
		checkPermission(getModuleName()+"_delete");
		try {
			SmsTemplate template = new SmsTemplate();
			template.setSmsTemplateId(id);
			template.setStatus(-1);
			smsTemplateService.update(template);
			//重置redis
			startInit.loadTemplate();	
			return getJSONResult(HttpStatusEnums.SUCCESS, null);
		} catch (Exception e) {
			log.info("/smsTemplate/admin/delete" + e);
			return getJSONResult(HttpStatusEnums.ERROR, null);
		}

	}
	
	/**
	 * 审核页面
	 */
	@RequestMapping(value = "admin/audit/{id}")
	public String audit(@PathVariable("id") Long id, 
			HttpServletRequest request, Model model) {
		SmsTemplate templ = smsTemplateService.selectById(id);
		model.addAttribute("item", templ);
		model.addAttribute("user", userService.selectById(templ.getUserId()));
		return "admin/" + getModuleName() + "/audit";
	}
	
	/**
	 * 模板审核
	 */
	@RequestMapping(value = "/admin/editAudit")
	public @ResponseBody Result update(SmsTemplate entity, BindingResult result, 
			HttpServletRequest request) {
		getService().update(entity);
		//重置redis
		startInit.loadTemplate();
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}

}
