package com.pj.core.tagSupport;

import com.pj.core.util.SpringContextUtils;
import com.pj.web.model.Property;
import com.pj.web.service.PropertyService;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class PropertySelectTag extends SimpleTagSupport {
	
	@Override
	public void doTag() throws JspException, IOException {
		Property property = new Property();
		property.setParentPath(this.parentPath);
		property.setStatus(1);
		List<Property> properties = propertyService.selectByParentPath(property);
		if (!CollectionUtils.isEmpty(properties)) {
			StringBuffer html = new StringBuffer();
			html.append("<select class=\"form-control\"");
			if (StringUtils.isNotBlank(this.id)) {
				html.append(" id=\"").append(this.id).append("\"");
			}
			if (StringUtils.isNotBlank(this.name)) {
				html.append(" name=\"").append(this.name).append("\"");
			}
			if (StringUtils.isNotBlank(this.required)) {
				html.append(" required=\"required\"");
			}
			html.append(">");

			if (StringUtils.isNotBlank(placeholder)) {
				html.append("<option value=\"\">").append(this.placeholder).append("</option>");
			}
			
			for (Property p : properties) {
				html.append("<option value=\"").append(p.getPropertyKey()).append("\"");
				if (StringUtils.isNotBlank(this.propertyKey) && this.propertyKey.equals(p.getPropertyKey())) {
					html.append(" selected=\"selected\"");
				}
				html.append(">").append(p.getPropertyValue()).append("</option>");
			}
			
			html.append("</select>");
			
			this.getJspContext().getOut().print(html.toString());
		}
	}
	
	private PropertyService propertyService = (PropertyService)SpringContextUtils.getBean("propertyServiceImpl");;
	
	// 数据字典父路径
	private String parentPath;
	
	// 选中的值
	private String propertyKey;

	// select的id属性
	private String id;
	
	// select的name属性
	private String name;
	
	// 提示项
	private String placeholder;
	
	// 是否必选
	private String required;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}
	
}
