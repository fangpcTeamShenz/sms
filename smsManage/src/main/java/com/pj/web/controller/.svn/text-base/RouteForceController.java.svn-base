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
import com.pj.web.model.Product;
import com.pj.web.model.RouteForce;
import com.pj.web.model.User;
import com.pj.web.service.ChannelService;
import com.pj.web.service.ProductService;
import com.pj.web.service.RouteForceService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/routeForce")
public class RouteForceController extends BaseController<RouteForce, Long> {

	@Resource
	private RouteForceService routeForceService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ChannelService channelService;
	
	@Resource(name="startInit")
	private StartInit startInit;
	
	@Override
	protected GenericService<RouteForce, Long> getService() {
		return routeForceService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_ROUTEFORCE;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"routeName","useable","channelName","productName","username","userId"};
	}
	
	@Override
	protected String list(Model model, RouteForce entity) {
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
	@Override
	protected String add(Model model, RouteForce entity) {
		//获取所有的短信用户
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null ,null));
		//获取产品信息
		model.addAttribute("products", productService.selectBySelective(new Product(), null ,null));
		//获取通道信息
		Channel channel = new Channel();
		channel.setUseable(Byte.valueOf("1"));
		model.addAttribute("channels", channelService.selectBySelective(channel, null ,null));
		return super.add(model, entity);
	}
	
	@Override
	protected String edit(Model model, @PathVariable("id") Long id) {
		//获取所有的短信用户
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null ,null));
		//获取产品信息
		model.addAttribute("products", productService.selectBySelective(new Product(), null ,null));
		//获取通道信息
		Channel channel = new Channel();
		channel.setUseable(Byte.valueOf("1"));
		model.addAttribute("channels", channelService.selectBySelective(channel, null ,null));
		model.addAttribute("item", routeForceService.selectById(id));
		return EDIT;
	}
	
	@Override
	public Result insert(RouteForce entity, BindingResult result) {
		getService().insert(entity);
		//重置redis
		startInit.loadProduct();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	@Override
	public Result update(RouteForce entity, BindingResult result, HttpServletRequest request) {
		getService().update(entity);
		//重置redis
		startInit.loadRouteForce();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
}
