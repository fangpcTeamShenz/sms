package com.pj.web.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pj.core.util.DateUtils;
import com.pj.web.model.User;
import com.pj.web.model.UserBill;
import com.pj.web.service.OrderSmsService;
import com.pj.web.service.UserBillService;
import com.pj.web.service.UserService;

/**
 * 凌晨统计账单
 * @author xt
 *
 */
@Component
public class CountUserBillTimer {

    private static Logger log = Logger.getLogger(CountUserBillTimer.class);
    
    @Resource
    private UserService userService;
    
    @Resource
    private OrderSmsService orderSmsService;
    
    @Resource
    private UserBillService userBillService;

    /**
     * 根据用户统计账单，生成一条账单记录
     */
    @Scheduled(cron = "0 0 0 * * ?")
    //@Scheduled(cron = "0 0/1 * * * ?")
    public void countUserBill(){
        try {
        	Map<String,Object> params = null;
			UserBill bill = null;
        	Calendar calender = Calendar.getInstance();
        	calender.add(Calendar.DATE, -1);//获取昨天日期
			String sendTime = DateUtils.format(calender.getTime(), "yyyy-MM-dd");
			User user = new User();
			user.setUserType(0);//用户
			List<User> users = userService.selectBySelective(user, null, null);
			for (User u : users) {
				params = new HashMap<String,Object>();
				params.put("userId", u.getUserId());
				params.put("sendTime", sendTime);
				Integer sum = orderSmsService.selectSumByYesterday(params);
				if(sum != null && sum > 0){
					bill = new UserBill();
					bill.setUserId(u.getUserId());
					bill.setAfterAmount(sum);
					bill.setFeeType(0);//消费
					bill.setCreateTime(new Date());
					userBillService.insert(bill);
				}
			}
		} catch (Exception e) {
			log.error("CountUserBillTimer error:", e);
		}
    }
   
}
