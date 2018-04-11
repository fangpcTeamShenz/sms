package com.pj.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.gereric.GenericService;
import com.pj.web.constant.AppConstants;
import com.pj.web.model.Property;
import com.pj.web.service.PropertyService;

@Controller
@RequestMapping("/property")
public class PropertyController extends BaseController<Property, Integer>{
	
	@Resource
	private PropertyService propertyService;

	@Override
	protected String getModuleName() {
		 return AppConstants.MODULE_PROPERTY;
	}

	@Override
	protected GenericService<Property, Integer> getService() {
		return propertyService;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"parentPath"};
	}
	
	@RequestMapping(value = "/admin/disable")
    public @ResponseBody Result update(Property property) {
		checkPermission(getModuleName()+"_update");
		
		propertyService.updateByDisable(property);
		
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	@RequestMapping(value = "/clearCache")
    public @ResponseBody Result clearCache() {
		
		propertyService.clearCache();
		
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
    }
	
	@Override
	protected Map<String, Object> getCriteria(HttpServletRequest request, String[] paras) {
		Map<String, Object> criteria = new HashMap<String, Object>();
    	if (paras != null && paras.length > 0) {
    		for (String key : paras) {
        		String value = request.getParameter(key);
        		if (StringUtils.isNotBlank(value)) {
        			try {
        				criteria.put(key.trim(), new String(value.trim().getBytes("ISO-8859-1"), "UTF-8"));
    				} catch (UnsupportedEncodingException e) {
    					log.error("Unsupported encoding exception ==>", e);
    				}
        		}
        	}
    	}
    	
    	if (SecurityUtils.getSubject().isPermitted("admin_permission")) {
    		//User user = getSessionUser(request.getSession());
    		criteria.put("disable", null);
		} else {
			criteria.put("disable", 0);
		}
    	
		return criteria;
	}
}
