package com.pj.web.controller.client;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.pj.web.constant.AppConstants;
import com.pj.web.controller.BaseController;
import com.pj.web.init.MongoConfig;
import com.pj.web.model.ErrorMsg;
import com.pj.web.model.OrderSms;
import com.pj.web.model.SmsSignature;
import com.pj.web.model.SmsTemplate;
import com.pj.web.model.User;
import com.pj.web.service.ErrorMsgService;
import com.pj.web.service.OrderSmsService;
import com.pj.web.service.SmsSignatureService;
import com.pj.web.service.SmsTemplateService;
import com.pj.web.service.UserService;

@Controller
@RequestMapping("/clientOrderSms")
public class ClientOrderSmsController extends BaseController<OrderSms, Long> {

	@Resource
	private OrderSmsService orderSmsService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private MongoConfig mongoConfig;

	@Resource
	private ErrorMsgService errorMsgService;
	
	@Resource
	private SmsTemplateService smsTemplateService;
	
	@Resource
	private SmsSignatureService smsSignatureService;
	
	@Override
	protected GenericService<OrderSms, Long> getService() {
		return orderSmsService;
	}

	@Override
	protected String getModuleName() {
		return AppConstants.MODULE_CLIENT_ORDERSMS;
	}

	@Override
	protected String[] getPageListCriteria() {
		return new String[]{"phone","orderSystemUniqueId","userId","status","sendTimeBegin","sendTimeEnd","completeTimeBegin","completeTimeEnd"};
	}
	
	@RequestMapping(value="/client/list")
	protected String list(Model model, OrderSms entity) {
		/*try {
			MongoClient client = mongoConfig.mongoClient();
			MongoDatabase database = mongoConfig.mongoDatabase(client);
			MongoCollection<Document> collection = mongoConfig.mongoCollection(database);
			FindIterable<Document> findIterable = collection.find();
			MongoCursor<Document> mongoCursor = findIterable.iterator();
			while (mongoCursor.hasNext()) {
				System.out.println(mongoCursor.next());
			}
			System.out.println("集合 test 选择成功");
		} catch (Exception e) {
			log.error(e);
		}*/
		User user = new User();
		user.setUserType(0);
		model.addAttribute("users", userService.selectBySelective(user, null, null));
		return "client/smsOrder/list";
	}
	
	@RequestMapping(value="/client/page/list")
	@ResponseBody
	protected Page<Map<String, Object>> pageList(HttpServletRequest request) {
		checkPermission(getModuleName()+"_view");
		MongoClient client = mongoConfig.mongoClient();
		MongoDatabase database = mongoConfig.mongoDatabase(client);
		MongoCollection<Document> collection = mongoConfig.mongoCollection(database);
		Page<Map<String, Object>> page = getPage(request);
    	Map<String, Object> criteria = getCriteria(request, getPageListCriteria());
    	User user = (User) request.getSession().getAttribute(SESSION_USER);
    	criteria.put("userId", user.getUserId());
    	List<Map<String, Object>> list = getService().selectByPageList(page, criteria);
    	for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
    		if(map.get("sms_content_id") != null) {
    			//内容从mongoDB中获取
    			try {
//    				FindIterable<Document> findIterable = collection.find(Filters.eq("_id", new BasicDBObject("$in", new String[] {"5a8e648bf55b3c574769fa3a","5a8e626ef55b3c569e2709d9"})));
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
    			ErrorMsg msg = errorMsgService.selectById(Integer.valueOf(map.get("error_msg_code").toString()));
    			map.put("errorMsg", msg.getErrorMsgText());
    		}
		}
    	//需要获取客户姓名、短信内容、失败原因
    	page.setResult(list);
    	return page;
	}
	
	@RequestMapping(value="/client/sendSms")
	protected String sendSms(Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(SESSION_USER);
		if(user == null) {
			return "/admin/login";
		}
		//可用的签名
		SmsSignature signature = new SmsSignature();
		signature.setUserId(user.getUserId());
		signature.setStatus(1);
		List<SmsSignature> signatures = smsSignatureService.selectBySelective(signature, null, null);
		//可用模板
		SmsTemplate template = new SmsTemplate();
		template.setUserId(user.getUserId());
		template.setStatus(1);
		List<SmsTemplate> templates = smsTemplateService.selectBySelective(template, null, null);
		model.addAttribute("signatures", signatures);
		model.addAttribute("templates", templates);
		return "client/smsOrder/sendSms";
	}
	
	@RequestMapping(value = "/client/send", method = RequestMethod.POST)
    public @ResponseBody Result send(HttpServletRequest request) {
		try {
			if(StringUtils.isEmpty(request.getParameter("phones")) || 
					StringUtils.isEmpty(request.getParameter("smsSignatureId")) || 
					StringUtils.isEmpty(request.getParameter("templateContent"))) {
				return getJSONResult(HttpStatusEnums.ERROR_PARAS, "参数不能为空");
			}
			HttpSession session = request.getSession();
			Result result = orderSmsService.sendSmsContent(request, session);
			if(!HttpStatusEnums.SUCCESS.toString().equals(result.getStatus())) {
				return getJSONResult(HttpStatusEnums.ERROR, result.getMessage());
			}
		} catch (Exception e) {
			log.error("send error:", e);
			return getJSONResult(HttpStatusEnums.EXCEPTION, "网络异常，请联系管理员！");
		}
    	return getJSONResult(HttpStatusEnums.SUCCESS, "发送成功");
    }
	
	/**
	 * 号码导入页面
	 * @return
	 */
	@RequestMapping("/client/import")
	public String  importPhone(){
		return "/client/smsOrder/import";
	}
	
	/**
	 * 号码导入
	 * @param uploadExcelFile
	 * @return
	 */
	@RequestMapping(value="/client/importPhone")
	public @ResponseBody Result importPhone(@PathVariable("uploadExcelFile") MultipartFile uploadExcelFile, HttpServletRequest request){
		String phones = "";
		try {
			String fileName = uploadExcelFile.getOriginalFilename();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);     
			if(!("xls".equals(suffix) || "xlsx".equals(suffix) || "txt".equals(suffix))){
				log.info("【导入文件名错误】");
				return getJSONResult(HttpStatusEnums.ERROR, "导入格式错误，请重新选如！");
			}
			phones = orderSmsService.analyzeImportPhones(uploadExcelFile, suffix);
			if(StringUtils.isEmpty(phones)){
				return getJSONResult(HttpStatusEnums.ERROR, "导入号码失败，请重新导入！");
			}
		} catch (Exception e) {
			log.error("importPhone error:", e);
			return getJSONResult(HttpStatusEnums.EXCEPTION, "网络错误，请稍后再试");
		}
    	return getJSONResult(HttpStatusEnums.SUCCESS, phones);
    }
	
}
