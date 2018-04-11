package com.pj.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.Channel;
import com.pj.web.service.ChannelService;

@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController<Channel, Long> {

	@Resource
	private ChannelService channelService;
	
	@Override
	protected GenericService<Channel, Long> getService() {
		return channelService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_CHANNEL;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"channelName","productType","existCallback","existUpper","useable"};
	}
	
}
