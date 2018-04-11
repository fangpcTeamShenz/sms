package com.pj.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.UserMapper;
import com.pj.web.dao.UserRoleMapper;
import com.pj.web.model.User;
import com.pj.web.model.UserRole;
import com.pj.web.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {

    @Resource
    private UserMapper userMapper;
    
    @Resource
    private UserRoleMapper userRoleMapper;
    
    public GenericDao<User, Long> getDao() {
    	return userMapper;
    }

    public User authentication(User user) {
        return userMapper.authentication(user);
    }

    public User selectByUsername(String username) {
        final List<User> list = userMapper.selectByUsername(username);
        if (list == null || list.isEmpty()) {
        	return null;
        }
        return list.get(0);
    }

	@Override
	public List<UserRole> selectrole(long id) {
		Map<String, Object> criteria=new HashMap<String, Object>();
		criteria.put("id", id);
			
		return userRoleMapper.selectList(criteria);
	}

	@Override
	public void updaterole(long id, long roleid) {
		userRoleMapper.deleteByuserid(id);
		UserRole userrole=new UserRole();
		userrole.setRoleId(roleid);
		userrole.setUserId(id);
		userRoleMapper.insertSelective(userrole);
		
	}

	@Override
	public void registerUser(User user) {
		// 默认给用户充值一块钱，记录日志
		
	}

	

	@Override
	public User selectByPhone(String phone) {
		return userMapper.selectByPhone(phone);
	}

	@Override
	public List<Map<String, Object>> selectSmsUserByPageList(Page<Map<String, Object>> page, Map<String, Object> criteria) {
		return userMapper.selectSmsUserByPageList(page,criteria);
	}

	@Override
	public List<Map<String, Object>> selectByParams(Map<String, Object> params) {
		return userMapper.selectByParams(params);
	}

	@Override
	public HSSFWorkbook exportExcel(List<Map<String, Object>> data) {
		HSSFWorkbook workBook = new HSSFWorkbook();// 创建 一个excel文档对象
		HSSFSheet sheet = workBook.createSheet();// 创建一个工作薄对象
		sheet.setColumnWidth(0, 8000);
		sheet.setColumnWidth(1, 8000);
		sheet.setColumnWidth(2, 8000);
		sheet.setColumnWidth(3, 8000);
		sheet.setColumnWidth(4, 8000);
		sheet.setColumnWidth(5, 8000);
		sheet.setColumnWidth(6, 8000);
		HSSFRow row = sheet.createRow(0);// 创建表头
		HSSFCell cell0 = row.createCell(0);
		cell0.setCellValue("用户名");
		HSSFCell cell1 = row.createCell(1);// 创建单元格
		cell1.setCellValue("用户组");
		HSSFCell cell2 = row.createCell(2);
		cell2.setCellValue("联系人");
		HSSFCell cell3 = row.createCell(3);
		cell3.setCellValue("联系电话");
		HSSFCell cell4 = row.createCell(4);
		cell4.setCellValue("邮箱");
		HSSFCell cell5 = row.createCell(5);
		cell5.setCellValue("账户余额");
		HSSFCell cell6 = row.createCell(6);
		cell6.setCellValue("余额预警金额");
		/*HSSFCell cell7 = row.createCell(7);
		cell7.setCellValue("创建时间");*/
		
		for(int i =0 ;i < data.size() ; i++){
			Map<String,Object> map = data.get(i);
			HSSFRow r = sheet.createRow(i+1);	// 从第二行开始创建数据
			
			HSSFCell c0 = r.createCell(0);
			c0.setCellValue(objectToString(map.get("nickname")));
			HSSFCell c1 = r.createCell(1);
			c1.setCellValue(objectToString(map.get("groupName")));
			HSSFCell c2 = r.createCell(2);
			c2.setCellValue(objectToString(map.get("linkman")));
			HSSFCell c3 = r.createCell(3);
			c3.setCellValue(objectToString(map.get("phone")));
			HSSFCell c4 = r.createCell(4);
			c4.setCellValue(objectToString(map.get("email")));
			HSSFCell c5 = r.createCell(5);
			c5.setCellValue(objectToString(map.get("balance")));
			HSSFCell c6 = r.createCell(6);
			c6.setCellValue(objectToString(map.get("alertBalance")));
			/*HSSFCell c7 = r.createCell(7);
			c7.setCellValue(objectToString(map.get("createTime")));*/
		}
		return workBook;
	}

	private String objectToString(Object object) {
		return object == null ? "": String.valueOf(object);
	}

	@Override
	public List<User> selectListBySelective(User user) {
		return userMapper.selectListBySelective(user);
	}

	@Override
	public List<User> selectByAlertBalanceNotify(Map<String, Object> params) {
		return userMapper.selectByAlertBalanceNotify(params);
	}

	@Override
	public void updateNotifySend() {
		userMapper.updateNotifySend();
	}

	@Override
	public boolean insertCreateSmsCode(String phone, String code) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> selectByUserIds(Map<String, Object> params) {
		return userMapper.selectByUserIds(params);
	}

	@Override
	@Cacheable(value="smsUser", key="#smsUser.userId + #smsUser.nickname")
	public User cacheSelectByUserId(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	@Override
	@CacheEvict(value={"smsUser"}, allEntries=true)
	public void clearCache() {
		log.info("清空用户缓存成功");
	}

	@Override
	public User selectByNickname(String nickname) {
		final List<User> list = userMapper.selectByNickname(nickname);
        if (list == null || list.isEmpty()) {
        	return null;
        }
	    return list.get(0);
	}

}
