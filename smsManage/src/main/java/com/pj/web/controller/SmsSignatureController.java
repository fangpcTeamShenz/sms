package com.pj.web.controller;

import java.util.Date;

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
import com.pj.web.model.SmsSignature;
import com.pj.web.model.User;
import com.pj.web.service.SmsSignatureService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/smsSignature")
public class SmsSignatureController extends BaseController<SmsSignature, Long> {

	@Resource
	private SmsSignatureService smsSignatureService;
	
	@Resource
	private UserService userService;
	
	@Resource(name="startInit")
	private StartInit startInit;
	
	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_SIGNATURE;
	}

	@Override
	protected GenericService<SmsSignature, Long> getService() {
		return smsSignatureService;
	}
	
	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"username","signatureType","signContent","status","userId"};
	}
	
	@Override
	protected String list(Model model, SmsSignature entity) {
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
	@Override
	protected String add(Model model, SmsSignature entity) {
		//获取所有的短信用户
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null ,null));
		return super.add(model, entity);
	}
	
	@Override
	protected String edit(Model model, @PathVariable("id") Long id) {
		SmsSignature signature = smsSignatureService.selectById(id);
		model.addAttribute("item", signature);
		model.addAttribute("user", userService.selectById(signature.getUserId()));
		return EDIT;
	}
	
	@Override
	public Result insert(SmsSignature entity, BindingResult result) {
		entity.setCreateTime(new Date());
		return super.insert(entity, result);
	}
	
	/**
	 * 软删除
	 */
	@RequestMapping(value = "admin/delete/{id}")
	public @ResponseBody
	Result delete(@PathVariable("id") Long id) {
		checkPermission(getModuleName()+"_delete");
		try {
			SmsSignature signature = new SmsSignature();
			signature.setSmsSignatureId(id);
			signature.setStatus(-1);
			smsSignatureService.update(signature);
			//重置redis
			startInit.loadSignature();
			return getJSONResult(HttpStatusEnums.SUCCESS, null);
		} catch (Exception e) {
			log.info("/smsSignature/admin/delete" + e);
			return getJSONResult(HttpStatusEnums.ERROR, null);
		}

	}
	
	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	@SystemLog(description = "修改")
    public @ResponseBody Result update(SmsSignature entity) {
		checkPermission(getModuleName()+"_edit");
		getService().update(entity);
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	/**
	 * 审核页面
	 */
	@RequestMapping(value = "admin/audit/{id}")
	public String audit(@PathVariable("id") Long id, 
			HttpServletRequest request, Model model) {
		model.addAttribute("item", smsSignatureService.selectById(id));
		String userId =  request.getParameter("userId");
		model.addAttribute("user", userService.selectById(Long.valueOf(userId)));
		return "admin/" + getModuleName() + "/audit";
	}
	
	/**
	 * 签名审核
	 */
	@RequestMapping(value = "admin/editAudit")
	public @ResponseBody Result update(SmsSignature entity, BindingResult result, 
			HttpServletRequest request) {
		//重置redis
		startInit.loadSignature();
		return super.update(entity, result, request);
	}

}
