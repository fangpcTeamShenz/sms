package com.pj.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {
	
	@RequestMapping({ "/", "/index" })
	public String index() {
		return "index";
	}

	@RequestMapping("/admin/index")
	public String adminIndex() {
		return "admin/index";
	}

	@RequestMapping("/admin/login")
	public String login() {
		return "admin/login";
	}

	@RequestMapping("/404")
	public String error404() {
		return "404";
	}

	@RequestMapping("/401")
	public String error401() {
		return "401";
	}

	@RequestMapping("/500")
	public String error500() {
		return "500";
	}
	
}