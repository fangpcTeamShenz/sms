package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.ProductMapper;
import com.pj.web.model.Product;
import com.pj.web.service.ProductService;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product, Long> implements ProductService {

    @Resource
    private ProductMapper productMapper;
    
    public GenericDao<Product, Long> getDao() {
    	return productMapper;
    }
}
