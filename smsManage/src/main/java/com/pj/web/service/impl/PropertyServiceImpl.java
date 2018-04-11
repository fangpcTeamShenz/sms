package com.pj.web.service.impl;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.PropertyMapper;
import com.pj.web.model.Property;
import com.pj.web.service.PropertyService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PropertyServiceImpl extends GenericServiceImpl<Property, Integer> implements PropertyService {

	@Resource
	private PropertyMapper propertyMapper;

	@Override
	public GenericDao<Property, Integer> getDao() {
		return propertyMapper;
	}

	@Override
	@Cacheable(value="property", key="#property.parentPath + #property.status + #property.propertyKey + #property.propertyName")
	public List<Property> selectByParentPath(Property property) {
		return propertyMapper.selectByParentPath(property);
	}
	
	@Override
	public void updateByDisable(Property property) {
		propertyMapper.updateByDisable(property);
	}

	@Override
	@CacheEvict(value={"property"}, allEntries=true)
	public void clearCache() {
		log.info("清空系统缓存成功");
	}
	
}
