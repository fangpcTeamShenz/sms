package com.pj.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pj.core.gereric.GenericDao;
import com.pj.core.gereric.GenericServiceImpl;
import com.pj.web.dao.ChargeMapper;
import com.pj.web.dao.UserBillMapper;
import com.pj.web.dao.UserMapper;
import com.pj.web.init.StartInit;
import com.pj.web.model.Charge;
import com.pj.web.model.User;
import com.pj.web.model.UserBill;
import com.pj.web.service.ChargeService;

@Service
public class ChargeServiceImpl extends GenericServiceImpl<Charge, Long> implements ChargeService {

    @Resource
    private ChargeMapper chargeMapper;
    
    @Resource
    private UserMapper userMapper;
    
    @Resource
    private UserBillMapper userBillMapper;
    
	@Resource(name="startInit")
	private StartInit startInit;
    
    public GenericDao<Charge, Long> getDao() {
    	return chargeMapper;
    }

	@Override
	public boolean updateBalance(Charge chage) {
		boolean bool = false;
		try {
			if(chage.getStatus() == 2) {//充值完成
				//修改账户余额
				User user = userMapper.selectByPrimaryKey(chage.getUserId());
				Long giftAmount = chage.getGiftAmount() == null ? 0 : chage.getGiftAmount();
				user.setBalance(chage.getChargeAmount() + giftAmount + user.getBalance());
				userMapper.updateByPrimaryKeySelective(user);
				//产生一条充值记录
				UserBill bill = new UserBill();
				bill.setUserId(chage.getUserId());
				bill.setFeeType(2);
				bill.setAfterAmount(chage.getChargeAmount().intValue() + giftAmount.intValue());
				userBillMapper.insertSelective(bill);
				//重置redis
				startInit.loadUserAccount();
			}
			chargeMapper.updateByPrimaryKeySelective(chage);
			bool = true;
		} catch (Exception e) {
			log.error("updateBalance error:", e);
		}
		return bool;
	}

}
