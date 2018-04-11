package com.pj.web.service;

import com.pj.core.gereric.GenericService;
import com.pj.web.model.Property;

import java.util.List;

public interface PropertyService extends GenericService<Property, Integer> {

	List<Property> selectByParentPath(Property property);
	
	void updateByDisable(Property property);
	
	void clearCache();
	
}
