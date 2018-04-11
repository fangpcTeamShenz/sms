package com.pj.core.util;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.pj.web.model.Property;
import com.pj.web.service.PropertyService;

public class PropertyUtils {
	
	private static PropertyService propertyService = (PropertyService) SpringContextUtils.getBean("propertyServiceImpl");

	public static Property selectByParentPathAndPropertyName (String parentPath, String propertyName) {
		Property p = new Property();
		p.setParentPath(parentPath);
		p.setPropertyName(propertyName);
		List<Property> list = propertyService.selectByParentPath(p);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}
	
}
