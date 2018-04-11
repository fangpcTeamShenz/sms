package com.pj.core.tagSupport;

import com.pj.core.util.SpringContextUtils;
import com.pj.web.model.Property;
import com.pj.web.service.PropertyService;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.collections.CollectionUtils;

public class PropertyValueTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Property property = new Property();
		property.setParentPath(this.parentPath);
		property.setPropertyKey(this.propertyKey);
		List<Property> properties = propertyService.selectByParentPath(property);
		if (!CollectionUtils.isEmpty(properties)) {
			this.getJspContext().getOut().print(properties.get(0).getPropertyValue());
		}
	}
	
	private PropertyService propertyService = (PropertyService)SpringContextUtils.getBean("propertyServiceImpl");

	// 数据字典父路径
	private String parentPath;
	
	// 值
	private String propertyKey;

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public String getPropertyKey() {
		return propertyKey;
	}

	public void setPropertyKey(String propertyKey) {
		this.propertyKey = propertyKey;
	}

}
