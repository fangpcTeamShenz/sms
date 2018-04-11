package com.pj.web.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericService;
import com.pj.web.model.User;
import com.pj.web.model.UserRole;

public interface UserService extends GenericService<User, Long> {

    /**
     * 用户验证
     * 
     * @param User
     * @return
     */
    User authentication(User User);

    /**
     * 根据用户名查询用户
     * 
     * @param Username
     * @return
     */
    User selectByUsername(String username);

    List<UserRole> selectrole(long id);

	void updaterole(long id, long roleid);
	
	void registerUser(User user);

	boolean insertCreateSmsCode(String phone, String code) throws Exception;

	User selectByPhone(String phone);

	List<Map<String, Object>> selectSmsUserByPageList(Page<Map<String, Object>> page, Map<String, Object> criteria);

	List<Map<String, Object>> selectByParams(Map<String, Object> params);

	HSSFWorkbook exportExcel(List<Map<String, Object>> dataList);

	List<User> selectListBySelective(User user);

	/**
	 * 查询余额小于预警金额的用户
	 * @param params
	 * @return
	 */
	List<User> selectByAlertBalanceNotify(Map<String, Object> params);

	void updateNotifySend();
	
	List<User> selectByUserIds(Map<String, Object> params);
    
	/**
	 * 缓存查询用户信息
	 * 方法描述：
	 * @Title: ehcacheSelectByUserId 
	 * @param @param userId
	 * @param @return
	 * @return User
	 * @user sohan
	 * 2018年3月1日
	 * @throws
	 */
	User cacheSelectByUserId(Long userId);
	
	void clearCache();
	
	User selectByNickname(String nickname);
}
