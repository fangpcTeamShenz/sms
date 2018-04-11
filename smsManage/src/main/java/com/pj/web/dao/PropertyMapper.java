package com.pj.web.dao;

import com.pj.core.gereric.GenericDao;
import com.pj.web.model.Property;

import java.util.List;

public interface PropertyMapper extends GenericDao<Property, Integer> {

	List<Property> selectByParentPath(Property property);
	
	void updateByDisable(Property property);
	
}