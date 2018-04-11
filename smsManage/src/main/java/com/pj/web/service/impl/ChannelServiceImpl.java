package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.ChannelMapper;
import com.pj.web.model.Channel;
import com.pj.web.service.ChannelService;

@Service
public class ChannelServiceImpl extends GenericServiceImpl<Channel, Long> implements ChannelService {

    @Resource
    private ChannelMapper channelMapper;
    
    public GenericDao<Channel, Long> getDao() {
    	return channelMapper;
    }
}
