package com.pj.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.init.StartInit;
import com.pj.web.model.Product;
import com.pj.web.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController<Product, Long> {

	@Resource
	private ProductService productService;
	
	@Resource(name="startInit")
	private StartInit startInit;
	
	@Override
	protected GenericService<Product, Long> getService() {
		return productService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_PRODUCT;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"ispCode","productType","productName"};
	}
	
	@Override
	public Result insert(Product entity, BindingResult result) {
		getService().insert(entity);
		//重置redis
		startInit.loadProduct();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
	@Override
	public Result update(Product entity, BindingResult result, HttpServletRequest request) {
		getService().update(entity);
		//重置redis
		startInit.loadProduct();
		return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
}
