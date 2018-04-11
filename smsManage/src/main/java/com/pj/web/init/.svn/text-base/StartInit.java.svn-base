package com.pj.web.init;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ServletContextAware;

import com.pj.core.threadPool.ThreadPoolProxyFactory;
import com.pj.service.redis.RedisUtil;
import com.pj.service.redis.constant.RedisConfig;
import com.pj.service.redis.model.RedisModule;
import com.pj.service.redis.model.RedisSmsSignature;
import com.pj.service.redis.model.RedisSmsTemplate;
import com.pj.service.redis.model.RedisUser;
import com.pj.web.dao.ModuleMapper;
import com.pj.web.dao.ProductMapper;
import com.pj.web.dao.RouteForceMapper;
import com.pj.web.dao.SmsSignatureMapper;
import com.pj.web.dao.SmsTemplateMapper;
import com.pj.web.dao.UserMapper;
import com.pj.web.model.Module;
import com.pj.web.model.Product;
import com.pj.web.model.RouteForce;
import com.pj.web.model.SmsSignature;
import com.pj.web.model.SmsTemplate;
import com.pj.web.model.User;

/**
 * 系统初始化加载参数
 * 注意,如果rds没有同步到,系统GG.前期把bug修复完毕在注释掉
 * @author Fangpc
 *
 */
public class StartInit implements InitializingBean, ServletContextAware {

	 static final Logger logger = Logger.getLogger(StartInit.class);

	 @Resource
	 private ProductMapper productMapper;
	 
	 @Resource
	 private UserMapper userMapper;
	 
	 @Resource
	 private SmsTemplateMapper smsTemplateMapper; 
	 
	 @Resource
	 private SmsSignatureMapper smsSignatureMapper;
	 
	 @Resource 
	 private RouteForceMapper routeForceMapper;
	 
	 @Resource
	 private ModuleMapper moduleMapper;

	@Override
	public void setServletContext(ServletContext arg0) {
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				Long startTime = System.currentTimeMillis();
				//产品数据处理
				loadProduct();
				//路由数据
				loadRouteForce();
				//模块
				loadModule();
				//模板
				loadTemplate();
				//签名
				loadSignature();
				//商户信息appsecret+账户+
				loadUserAccount();
				logger.info("【setServletContext加载启动数据耗时----"+(System.currentTimeMillis()-startTime)/1000+"(s)----】");
			}
		});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}
	
	/**
	 * 加载产品
	 */
	public void loadProduct(){
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				try {
					Long startTime = System.currentTimeMillis();
					Map<String, Object> criteria = new HashMap<String, Object>();
					List<Product> productList = productMapper.selectList(criteria);
					if(CollectionUtils.isNotEmpty(productList)){
						Map<String, String> temp = new HashMap<>();
						for (Product product : productList) {
							temp.put(RedisConfig.CONS_PRODUCT+product.getIspCode()+product.getProductType(), String.valueOf(product.getProductId()));
						}
						RedisUtil.setValArr(temp, null) ;
					}
					logger.info("【产品数据处理加载启动数据耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】");
				} catch (Exception e) {
					logger.error("loadProduct()", e);
					System.exit(0);//程序GG
				}
			}
		});
	}
	
	/**
	 * 路由数据处理
	 */
	public void loadRouteForce(){
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				try {
					Long startTime = System.currentTimeMillis();
					Map<String, Object> criteria = new HashMap<String, Object>();
					criteria.put("useable", "1");
					List<RouteForce> routeForceList = routeForceMapper.selectList(criteria);
					if(CollectionUtils.isNotEmpty(routeForceList)){
						Map<String, List<RouteForce>> productRouteForceList = new HashMap<>();
						List<RouteForce> tmpRouteForceList = null;
						for (RouteForce routeForce : routeForceList) {
							if(routeForce.getUserId()!=null && routeForce.getUserId()!=0){
								tmpRouteForceList = productRouteForceList.get(routeForce.getProductId()+"_"+routeForce.getUserId());
								if(CollectionUtils.isEmpty(tmpRouteForceList)){
									tmpRouteForceList = new ArrayList<>();
								}
								tmpRouteForceList.add(routeForce);
								sort(tmpRouteForceList);
								productRouteForceList.put(routeForce.getProductId()+"_"+routeForce.getUserId(), tmpRouteForceList);
							}else{
								tmpRouteForceList = productRouteForceList.get(routeForce.getProductId().toString());
								if(CollectionUtils.isEmpty(tmpRouteForceList)){
									tmpRouteForceList = new ArrayList<>();
								}
								tmpRouteForceList.add(routeForce);
								sort(tmpRouteForceList);
								productRouteForceList.put(routeForce.getProductId().toString(), tmpRouteForceList);
							}
						}
						List<String> tmpChannelIdlist = null;
						for (Map.Entry<String, List<RouteForce>> entry : productRouteForceList.entrySet()) {
							tmpChannelIdlist = new ArrayList<>();
							tmpChannelIdlist.add(entry.getValue().get(0).getChannelId().toString());
							if(entry.getKey().indexOf("_")>-1){
								RedisUtil.setList(RedisConfig.CONS_USER_CHANNEL_LIST + entry.getKey(), tmpChannelIdlist, null);
							}else{
								RedisUtil.setList(RedisConfig.CONS_CHANNEL_LIST + entry.getKey(), tmpChannelIdlist, null);
							}
							
						}
					}
					logger.info("【路由数据处理加载启动数据耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】");
				} catch (Exception e) {
					logger.error("loadRoute()", e);
					System.exit(0);//程序GG
				}
			}
		});
	}
	 
	/**
	 * 加载模块
	 */
	public void loadModule(){
		//加载端口
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				try {
					Long startTime = System.currentTimeMillis();
					Map<String, Object> criteria = new HashMap<String, Object>();
					criteria.put("useable", "1");
					List<Module> moduleList = moduleMapper.selectList(criteria);
					if(CollectionUtils.isNotEmpty(moduleList)){
						Map<String, List<String>> channelModuleList = new HashMap<>();
						List<String> tmpModuleList = null;
						RedisModule redisModule = null;
						for (Module module : moduleList) {
							redisModule = new RedisModule();
							redisModule.setA(module.getIdentityCode());
							redisModule.setC(module.getModuleUrl());
							redisModule.setD(module.getModuleKey());
							RedisUtil.setObj(RedisConfig.CONS_MODULE+module.getModuleId(), redisModule, null);
							tmpModuleList = channelModuleList.get(module.getChannelId().toString());
							if(CollectionUtils.isEmpty(tmpModuleList)){
								tmpModuleList = new ArrayList<>();
							}
							tmpModuleList.add(module.getModuleId().toString());
							channelModuleList.put(module.getChannelId().toString(), tmpModuleList);
						}
						for (Map.Entry<String, List<String>> entry : channelModuleList.entrySet()) {
							RedisUtil.setList(RedisConfig.CONS_MODULE_LIST + entry.getKey(), entry.getValue(), null);
						}
					}
					logger.info("【端口数据处理加载启动数据耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】");
				} catch (Exception e) {
					logger.error("loadModule()", e);
					System.exit(0);//程序GG
				}
			}
		});
	}	
	
	/**
	 * 加载模板
	 */
	public void loadTemplate(){
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				try {
					Long startTime = System.currentTimeMillis();
					Map<String, Object> criteria = new HashMap<String, Object>();
					criteria.put("status", "1");
					List<SmsTemplate> templateList = smsTemplateMapper.selectList(criteria);
					if(CollectionUtils.isNotEmpty(templateList)){
						List<String> valList = null;
						RedisSmsTemplate redisTemplate = null;
						Map<String, List<String>> templatesMap = new HashMap<>();
						for (SmsTemplate template : templateList) {
							valList = templatesMap.get(RedisConfig.MER_TEMPLATE_LIST + template.getUserId());
							if(valList==null){
								valList = new ArrayList<>();
								valList.add(template.getTemplateContent()+"_"+ String.valueOf(template.getSmsTemplateId()));
							}else{
								valList.add(template.getTemplateContent()+"_"+ String.valueOf(template.getSmsTemplateId()));
							}
							templatesMap.put(RedisConfig.MER_TEMPLATE_LIST + template.getUserId(), valList);
							//保存模板
							redisTemplate = new RedisSmsTemplate();
							redisTemplate.setA(template.getProductType().intValue());
							RedisUtil.setObj(RedisConfig.CONS_TEMPLATE+template.getSmsTemplateId(), redisTemplate,null);
						}
						RedisUtil.setListArr(templatesMap, null);
					}
					logger.info("【模板数据处理加载启动数据耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】");
				} catch (Exception e) {
					logger.error("loadTemplate()", e);
					System.exit(0);//程序GG
				}
			}
		});
	}
	
	/**
	 * 加载签名
	 */
	public void loadSignature(){
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				try {
					Long startTime = System.currentTimeMillis();
					Map<String, Object> criteria = new HashMap<String, Object>();
					criteria.put("status", "1");
					List<SmsSignature> signatureList = smsSignatureMapper.selectList(criteria);
					if(CollectionUtils.isNotEmpty(signatureList)){
						List<String> valList = null;
						RedisSmsSignature redisSignature = null;
						Map<String, List<String>> signatureMap = new HashMap<>();
						for (SmsSignature signature : signatureList) {
							valList = signatureMap.get(RedisConfig.MER_SIGNATURE_LIST + signature.getUserId());
							if(valList==null){
								valList = new ArrayList<>();
								valList.add(signature.getSignContent()+"_"+ String.valueOf(signature.getSmsSignatureId()));
							}else{
								valList.add(signature.getSignContent()+"_"+ String.valueOf(signature.getSmsSignatureId()));
							}
							signatureMap.put(RedisConfig.MER_SIGNATURE_LIST + signature.getUserId(), valList);
							//保存签名
							redisSignature = new RedisSmsSignature();
							redisSignature.setA(signature.getProductType());
							redisSignature.setB(signature.getSignatureType());
							RedisUtil.setObj(RedisConfig.CONS_SIGNATURE+signature.getSmsSignatureId(), redisSignature,null);
						}
						RedisUtil.setListArr(signatureMap, null);
					}
					logger.info("【签名数据处理加载启动数据耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】");
				} catch (Exception e) {
					logger.error("loadSignature()", e);
					System.exit(0);//程序GG
				}
			}
		});
	}
	
	/**
	 * 加载用户信息appsecret+账户+
	 */
	public void loadUserAccount(){
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				try {
					Long startTime = System.currentTimeMillis();
					Map<String, Object> criteria = new HashMap<String, Object>();
					criteria.put("userType", "0");
					List<User> userList = userMapper.selectList(criteria);
					if(CollectionUtils.isNotEmpty(userList)){
						RedisUser redisUser = null;
						Map<String, String> merAccountMap = new HashMap<>();
						for (User user : userList) {
							merAccountMap.put(user.getUsername(),String.valueOf(user.getUserId()));
							redisUser = new RedisUser();
							redisUser.setA(user.getAppsecret());
							redisUser.setB(user.getPhone());
							redisUser.setC(user.getEmail());
							redisUser.setD(user.getBalance().longValue());
							redisUser.setE(user.getCreditAmount().longValue());
							redisUser.setF(user.getAlertBalance().longValue());
							redisUser.setG(user.getAlertPhone());
							redisUser.setI(user.getUpstreamUrl());
							redisUser.setJ(user.getNotifyUrl());
							RedisUtil.setObj(RedisConfig.MER_INFO+user.getUserId(), redisUser,null);
							if(StringUtils.isNotBlank(user.getKeywordWhite())){//敏感词白名单
								RedisUtil.setList(RedisConfig.MER_KEYWORD_WHITELIST+user.getUserId(), java.util.Arrays.asList(user.getKeywordWhite().split("\\|")), null);
							}
							if(StringUtils.isNotBlank(user.getExtNumber())){
								List<String> tmpUserList = RedisUtil.getList(RedisConfig.CONS_NUMBER_USER_LIST+user.getExtNumber(), 0, -1);
								if(CollectionUtils.isEmpty(tmpUserList)){
									tmpUserList = new ArrayList<>();
								}
								tmpUserList.add(user.getUserId().toString());
								RedisUtil.setList(RedisConfig.CONS_NUMBER_USER_LIST+user.getExtNumber(), tmpUserList, null);
							}
						}
						RedisUtil.setMap(RedisConfig.MER_ACCOUNT_INFO, merAccountMap, null);
					}
					logger.info("【商户信息处理加载启动数据耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】");
				} catch (Exception e) {
					logger.error("loadmerAccount()", e);
					System.exit(0);//程序GG
				}
			}
		});
	}
	
	/**
	 * list排序(升序)
	 * @param list
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void sort(List<RouteForce> list) {
		Collections.sort(list, new Comparator() {
			public int compare(Object a, Object b) {
				RouteForce ra = (RouteForce) a;
				RouteForce rb = (RouteForce) b;
				return ra.getPriority() - rb.getPriority();
			}
		});
	}
	
	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}
	
}
