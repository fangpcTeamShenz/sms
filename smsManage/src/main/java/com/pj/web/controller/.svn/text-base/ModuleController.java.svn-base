package com.pj.web.controller;

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
import com.pj.web.constant.AppConstants;
import com.pj.web.init.StartInit;
import com.pj.web.model.Channel;
import com.pj.web.model.Module;
import com.pj.web.service.ChannelService;
import com.pj.web.service.ModuleService;

@Controller
@RequestMapping("/module")
public class ModuleController extends BaseController<Module, Long> {

	@Resource
	private ModuleService moduleService;
	
	@Resource
	private ChannelService channelService;
	
	@Resource(name="startInit")
	private StartInit startInit;
	
	@Override
	protected GenericService<Module, Long> getService() {
		return moduleService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_MODULE;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"channelName","moduleName","useable"};
	}
	
	@Override
	protected String add(Model model, Module entity) {
		//获取通道信息
		Channel channel = new Channel();
		channel.setUseable(Byte.valueOf("1"));
		model.addAttribute("channels", channelService.selectBySelective(channel, null ,null));
		return super.add(model, entity);
	}
	
	@Override
	protected String edit(Model model, @PathVariable("id") Long id) {
		//获取通道信息
		Channel channel = new Channel();
		channel.setUseable(Byte.valueOf("1"));
		model.addAttribute("channels", channelService.selectBySelective(channel, null ,null));
		return super.edit(model, id);
	}
	
	@Override
	public Result insert(Module entity, BindingResult result) {
		getService().insert(entity);
		//重置redis
		startInit.loadModule();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	@Override
	public Result update(Module entity, BindingResult result, HttpServletRequest request) {
		getService().update(entity);
		//重置redis
		startInit.loadModule();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
}
