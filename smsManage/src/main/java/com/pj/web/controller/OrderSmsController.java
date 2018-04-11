package com.pj.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.pj.core.entity.Result;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.feature.orm.mybatis.Page;
import com.pj.core.gereric.GenericService;
import com.pj.core.util.DateUtils;
import com.pj.web.constant.AppConstants;
import com.pj.web.init.MongoConfig;
import com.pj.web.model.ErrorMsg;
import com.pj.web.model.OrderSms;
import com.pj.web.model.User;
import com.pj.web.service.ErrorMsgService;
import com.pj.web.service.OrderSmsService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/orderSms")
public class OrderSmsController extends BaseController<OrderSms, Long> {

	@Resource
	private OrderSmsService orderSmsService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MongoConfig mongoConfig;
	
	@Resource
	private ErrorMsgService errorMsgService;
	
	@Override
	protected GenericService<OrderSms, Long> getService() {
		return orderSmsService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_ORDER_SMS;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"phone","orderSystemUniqueId","userId","status","sendTimeBegin","sendTimeEnd","completeTimeBegin","completeTimeEnd"};
	}
	
	@Override
	protected String list(Model model, OrderSms entity) {
		/*try {
			MongoClient client = mongoConfig.mongoClient();
			MongoDatabase database = mongoConfig.mongoDatabase(client);
			MongoCollection<Document> collection = mongoConfig.mongoCollection(database);
			FindIterable<Document> findIterable = collection.find(Filters.eq("_id", new BasicDBObject("$in", new String[] {"5a8e648bf55b3c574769fa3a","5a8e626ef55b3c569e2709d9"})));
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next().get("_id"));
			}
			System.out.println("集合 test 选择成功");
		} catch (Exception e) {
			log.error(e);
		}*/
		model.addAttribute("nowDate", DateUtils.format(new Date(), "yyyy-MM-dd"));
		
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return super.list(model, entity);
	}
	
	@RequestMapping(value="/admin/page/list")
	@ResponseBody
	protected Page<Map<String, Object>> pageList(HttpServletRequest request) {
		checkPermission(getModuleName()+"_view");
		MongoClient client = mongoConfig.mongoClient();
		MongoDatabase database = mongoConfig.mongoDatabase(client);
		MongoCollection<Document> collection = mongoConfig.mongoCollection(database);
		Page<Map<String, Object>> page = getPage(request);
    	Map<String, Object> criteria = getCriteria(request, getPageListCriteria());
    	List<Map<String, Object>> list = getService().selectByPageList(page, criteria);
    	log.info("list size:" + list.size());
    	for (int i = 0; i < list.size(); i++) {
	    	try {
    			Map<String, Object> map = list.get(i);
    			if(map.get("user_id") != null) {
        			User user = userService.selectById(Long.valueOf(map.get("user_id").toString()));//userService.cacheSelectByUserId(Long.valueOf(map.get("user_id").toString()));
        			map.put("nickname", user.getNickname());
        		}
        		if(map.get("sms_content_id") != null) {
        			//内容从mongoDB中获取
        			try {
//        				FindIterable<Document> findIterable = collection.find(Filters.eq("_id", new BasicDBObject("$in", new String[] {"5a8e648bf55b3c574769fa3a","5a8e626ef55b3c569e2709d9"})));
        				FindIterable<Document> findIterable = collection.find(Filters.eq("_id", map.get("sms_content_id").toString()));
        				MongoCursor<Document> mongoCursor = findIterable.iterator();
        				while (mongoCursor.hasNext()) {
        					map.put("smsContent", mongoCursor.next().get("smsContent"));
        				}
        			} catch (Exception e) {
        				log.error(e);
        			}
        		}
        		if(map.get("error_msg_code") != null) {
//        			ErrorMsg eMsg = new ErrorMsg();
//        			eMsg.setErrorMsgCode(Integer.valueOf(map.get("error_msg_code").toString()));
//        			ErrorMsg msg = errorMsgService.selectByErroMsg(eMsg);
        			ErrorMsg msg = errorMsgService.selectById(Integer.valueOf(map.get("error_msg_code").toString()));
        			map.put("errorMsg", msg.getErrorMsgText());
        		}
	    		
			} catch (Exception e) {
				log.error(e);
			}
    	}
    	//需要获取客户姓名、短信内容、失败原因
    	page.setResult(list);
    	return page;
	}
	
	/**
	 * 手动回调操作
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/callback")
	protected @ResponseBody Result callback(HttpServletRequest request) {
		try {
			Map<String, Object> criteria = getCriteria(request, getPageListCriteria());
	    	if(criteria.get("userId") == null){
	    		return getJSONResult(HttpStatusEnums.CUSTOM_MESSAGE, "请选择用户");
	    	}
	    	if(!((criteria.get("sendTimeBegin") != null && criteria.get("sendTimeEnd") != null)
	    			|| (criteria.get("completeTimeBegin") != null && criteria.get("completeTimeEnd") != null))){
	    		return getJSONResult(HttpStatusEnums.CUSTOM_MESSAGE, "请选择发送时间或完成时间");
	    	}
	    	boolean bool = orderSmsService.manualCallback(criteria);
	    	if(!bool){
	    		return getJSONResult(HttpStatusEnums.ERROR, "回调失败，请重试！");
	    	}
		} catch (Exception e) {
			log.error("manualCallback error:", e);
			return getJSONResult(HttpStatusEnums.EXCEPTION, "网络异常，请稍后再试！");
		}
    	return getJSONResult(HttpStatusEnums.SUCCESS, null);
	}
	
}
