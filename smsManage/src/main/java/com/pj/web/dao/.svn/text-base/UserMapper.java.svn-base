package com.pj.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericDao;
import com.pj.web.model.User;

public interface UserMapper extends GenericDao<User, Long>{
	/**
     * 用户登录验证查询
     * 
     * @param record
     * @return
     */
    User authentication(@Param("record") User record);
    
    /**
     * 根据用户名查询用户
     * 
     * @param username
     * @return
     */
    List<User> selectByUsername(@Param("username") String username);
    
    List<User> selectByNickname(@Param("nickname") String nickname);

	User selectByPhone(@Param("phone")String phone);

	List<Map<String, Object>> selectSmsUserByPageList(Page<Map<String, Object>> page, Map<String, Object> criteria);

	List<Map<String, Object>> selectByParams(Map<String, Object> params);

	List<User> selectListBySelective(@Param("model")User user);

	List<User> selectByAlertBalanceNotify(Map<String, Object> params);

	void updateNotifySend();
	
	List<User> selectByUserIds(Map<String, Object> params);
}