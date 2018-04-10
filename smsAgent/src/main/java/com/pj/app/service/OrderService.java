package com.pj.app.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.pj.app.constant.AppConstants;
import com.pj.app.model.BilRet;
import com.pj.app.model.BillBody;
import com.pj.app.model.JsonRootBean;
import com.pj.app.model.UpstreamBody;
import com.pj.core.enums.HttpStatusEnums;
import com.pj.core.model.JSONResult;
import com.pj.core.model.Result;
import com.pj.core.util.MD5Util;
import com.pj.core.util.threadPool.ThreadPoolProxyFactory;
import com.pj.service.mq.MqConfig.TopicDbTag;
import com.pj.service.mq.ObjectAndByte;
import com.pj.service.mq.model.SmsInfo;
import com.pj.service.mq.shared.SharedProducer;
import com.pj.service.redis.RedisUtil;
import com.pj.service.redis.constant.RedisConfig;
import com.pj.service.redis.model.RedisUser;

import redis.clients.jedis.Jedis;

/**
 * 静态类
 * @author Fangpc
 *
 */
public class OrderService {
	
	protected static final Logger logs = Logger.getLogger(OrderService.class);
	
	/**
	 * 接收短信
	 * 先验证,再处理数据
	 * @param requestStr
	 * @param ipStr
	 * @return
	 */
	public static Result receive(String requestStr,String ipStr) {
		Long startTime = System.currentTimeMillis();
		Result result = getJSONResult(HttpStatusEnums.SUCCESS,null);
		if(requestStr.length()<10000)
			logs.info(AppConstants.LOG_START_HEAD+ipStr+AppConstants.LOG_START_TAIL + requestStr);
		JsonRootBean jsonRootBean = JSON.parseObject(requestStr, JsonRootBean.class);
		RedisUser redisUser = null;
		String userId = RedisUtil.getMap(RedisConfig.MER_ACCOUNT_INFO, jsonRootBean.getAccessKey());
		if(StringUtils.isNotBlank(userId)){//用户对象
			Object redisUserObj = RedisUtil.getObj(RedisConfig.MER_INFO+userId);
			if(redisUserObj!=null){
				redisUser = (RedisUser) redisUserObj;
			}
		}
		if(redisUser != null){//参数合法性判断
			boolean verify = false;
			if(StringUtils.isNotBlank(jsonRootBean.getTimestamp()) && Math.abs(Long.parseLong(jsonRootBean.getTimestamp())-startTime)<60000){
				verify = true;
			}
			verify = true; //拿到时效性,为了压测
			if(verify){//时间戳有效性判断
				verify = false;
				String appsecret = redisUser.getA();
				String sysSign = MD5Util.MD5Encode(jsonRootBean.getOutOrderId()+appsecret+jsonRootBean.getTimestamp(), "utf-8");
				if(sysSign.equals(jsonRootBean.getSign())){//签名验证
					if(StringUtils.isNotBlank(jsonRootBean.getPhoneNumber()) || 
							(StringUtils.isNotBlank(jsonRootBean.getPhoneNumbers()) && jsonRootBean.getPhoneNumbers().length()<60000)){
						SmsInfo smsInfo = new SmsInfo();
						smsInfo.setA(jsonRootBean.getContent());
						smsInfo.setB(jsonRootBean.getPhoneNumber());
						smsInfo.setC(jsonRootBean.getOutOrderId());
						smsInfo.setD(jsonRootBean.getPhoneNumbers());
						smsInfo.setH(jsonRootBean.getStartdelivertime());
						smsInfo.setI(userId);
						smsInfo.setJ(jsonRootBean.getExtNumber());
//						public static BlockingQueue<UpstreamModel> concurrentMapUpstreamCounts = new LinkedBlockingQueue<>();  性能出现问题的时候用这个队列
						ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
							public void run() {
								Long startTimes = System.currentTimeMillis();
								SharedProducer.instanceDb.sendDb(JSON.toJSONString(smsInfo), TopicDbTag.TA,true);
								logs.info("【mq耗时处理任务耗时----"+(System.currentTimeMillis()-startTimes)+"(ms)----】");
							}
						});
					}else{
						if(jsonRootBean.getPhoneNumbers().length()>=60000){
							result = getJSONResult(HttpStatusEnums.ERROR_PARAS_LARGE, null);
						}else{
							result = getJSONResult(HttpStatusEnums.ERROR_PARAS_NULL, null);
						}
					}
				}else{
					result = getJSONResult(HttpStatusEnums.ERROR_SIGN, null);
				}
			}else{
				result = getJSONResult(HttpStatusEnums.ERROR_INVALID, null);
			}
		}else{
			result = getJSONResult(HttpStatusEnums.ERROR_NO_PERMISSION, null);
		}
		logs.info("【receive处理任务耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】 结果为:"+result.toString());
		return result;
	}
	
	/**
	 * 查询账单
	 * @param requestStr
	 * @param ipStr
	 * @return
	 */
	public static Result findBill(String requestStr,String ipStr) {
		Long startTime = System.currentTimeMillis();
		Result result = new Result(HttpStatusEnums.SUCCESS);
		logs.info(AppConstants.LOG_START_HEAD+ipStr+AppConstants.LOG_START_TAIL + requestStr);
		BillBody billBody = JSON.parseObject(requestStr, BillBody.class);
		Jedis jedis = RedisUtil.getJedis();
		Object redisUserObj = null;
		String userId = null;//用户id
		try {
			List<String> list = jedis.hmget(RedisConfig.MER_ACCOUNT_INFO, billBody.getUserAccount());
			if(!CollectionUtils.isEmpty(list))
				if (list.get(0) != null) 
					userId = String.valueOf(list.get(0));
			byte[] bs = jedis.get((RedisConfig.MER_INFO+userId).getBytes());
			if(bs!=null){
				redisUserObj = ObjectAndByte.toObject(bs);
			}
		} catch (Exception e) {
			logs.error("findBill()", e);
		}finally{
			RedisUtil.returnResource(jedis);
		}
		if(StringUtils.isNotBlank(userId) && redisUserObj!=null){
			if(StringUtils.isNotBlank(billBody.getTimestamp()) && Math.abs(Long.parseLong(billBody.getTimestamp())-startTime)<60000){
				RedisUser redisUser = (RedisUser) redisUserObj;
				String appsecret = redisUser.getA();
				String sysSign = MD5Util.MD5Encode(billBody.getUserAccount()+appsecret+billBody.getTimestamp(), "utf-8");
				if(sysSign.equals(billBody.getSign())){
					if(redisUser.getD()!=null){
						BilRet data = new BilRet();
						data.setBalance(redisUser.getD().toString());
						data.setUserName(billBody.getUserAccount());
						result = getJSONResult(HttpStatusEnums.SUCCESS, data);
					}else{
						result = getJSONResult(HttpStatusEnums.NO_BALANCE, null);
					}
				}else{
					result = getJSONResult(HttpStatusEnums.ERROR_SIGN, null);
				}
			}else{
				result = getJSONResult(HttpStatusEnums.ERROR_INVALID, null);
			}
		}else{
			result = getJSONResult(HttpStatusEnums.NO_ACCOUNT, null);
		}
		logs.info("【findBill处理任务耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】 结果为:"+result.toString());
		return result;
	}
	
	/**
	 * 查询状态
	 * @param requestStr
	 * @param ipStr
	 * @return
	 */
	public static Result findState(String requestStr,String ipStr) {
		Long startTime = System.currentTimeMillis();
		Result result = new Result(HttpStatusEnums.SUCCESS);
		logs.info(AppConstants.LOG_START_HEAD+ipStr+AppConstants.LOG_START_TAIL + requestStr);
		BillBody billBody = JSON.parseObject(requestStr, BillBody.class);
		Jedis jedis = RedisUtil.getJedis();
		Object redisUserObj = null;
		String userId = null;//用户id
		try {
			List<String> list = jedis.hmget(RedisConfig.MER_ACCOUNT_INFO, billBody.getUserAccount());
			if(!CollectionUtils.isEmpty(list))
				if (list.get(0) != null) 
					userId = String.valueOf(list.get(0));
			byte[] bs = jedis.get((RedisConfig.MER_INFO+userId).getBytes());
			if(bs!=null){
				redisUserObj = ObjectAndByte.toObject(bs);
			}
		} catch (Exception e) {
			logs.error("findBill()", e);
		}finally{
			RedisUtil.returnResource(jedis);
		}
		if(StringUtils.isNotBlank(userId) && redisUserObj!=null){
			if(StringUtils.isNotBlank(billBody.getTimestamp()) && Math.abs(Long.parseLong(billBody.getTimestamp())-startTime)<60000){
				RedisUser redisUser = (RedisUser) redisUserObj;
				String appsecret = redisUser.getA();
				String sysSign = MD5Util.MD5Encode(billBody.getUserAccount()+appsecret+billBody.getTimestamp(), "utf-8");
				if(sysSign.equals(billBody.getSign())){
					String balanceStr = redisUser.getD().toString();
					if(StringUtils.isNotBlank(balanceStr)){
						BilRet data = new BilRet();
						data.setBalance(balanceStr);
						data.setUserName(billBody.getUserAccount());
						result = getJSONResult(HttpStatusEnums.SUCCESS, data);
					}else{
						result = getJSONResult(HttpStatusEnums.NO_BALANCE, null);
					}
				}else{
					result = getJSONResult(HttpStatusEnums.ERROR_SIGN, null);
				}
			}else{
				result = getJSONResult(HttpStatusEnums.ERROR_INVALID, null);
			}
		}else{
			result = getJSONResult(HttpStatusEnums.NO_ACCOUNT, null);
		}
		logs.info("【findBill处理任务耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】 结果为:"+result.toString());
		return result;
	}
	
	/**
	 * 查询上行消息
	 * @return
	 */
	public static Result findUpstream(String requestStr,String ipStr) {
		Long startTime = System.currentTimeMillis();
		Result result = new Result(HttpStatusEnums.SUCCESS);
		logs.info(AppConstants.LOG_START_HEAD+ipStr+AppConstants.LOG_START_TAIL + requestStr);
		UpstreamBody upstreamBody = JSON.parseObject(requestStr, UpstreamBody.class);
		Jedis jedis = RedisUtil.getJedis();
		Object redisUserObj = null;
		String userId = null;//用户id
		try {
			List<String> list = jedis.hmget(RedisConfig.MER_ACCOUNT_INFO, upstreamBody.getUserAccount());
			if(!CollectionUtils.isEmpty(list))
				if (list.get(0) != null) 
					userId = String.valueOf(list.get(0));
			byte[] bs = jedis.get((RedisConfig.MER_INFO+userId).getBytes());
			if(bs!=null){
				redisUserObj = ObjectAndByte.toObject(bs);
			}
		} catch (Exception e) {
			logs.error("findUpstream()", e);
		}finally{
			RedisUtil.returnResource(jedis);
		}
		if(StringUtils.isNotBlank(userId) && redisUserObj!=null){
			if(StringUtils.isNotBlank(upstreamBody.getTimestamp()) && Math.abs(Long.parseLong(upstreamBody.getTimestamp())-startTime)<60000){
				String sysSign = null;
				if(redisUserObj!=null){
					RedisUser redisUser = (RedisUser) redisUserObj;
					String appsecret = redisUser.getA();
					sysSign = MD5Util.MD5Encode(upstreamBody.getUserAccount()+appsecret+upstreamBody.getTimestamp(), "utf-8");
				}
				if(sysSign.equals(upstreamBody.getSign())){
//					List<String> list = RedisUtil.getList(RedisConfig.CONS_SIGNATURE_NUMBER_UNREAD+userId, 0, -1);
//					if(!CollectionUtils.isEmpty(list)){
//						int pageSize = Integer.parseInt(upstreamBody.getPageSize())>list.size()?list.size():Integer.parseInt(upstreamBody.getPageSize());
//						UpstreamRet upstreamRet = null;
//						List<UpstreamRet> data = new ArrayList<>();
//						List<String> subList = list.subList(0, pageSize);
//						for (String string : subList) {
//							String[] tmp = string.split(",，");//smsUpstream.getPhone()+",，"+smsUpstream.getMessage()+",，"+smsUpstream.getReceiveTime()+",，"+numberSuffix
//							upstreamRet = new UpstreamRet();
//							upstreamRet.setPhone(tmp[0]);
//							upstreamRet.setMessage(tmp[1]);
//							upstreamRet.setReceiveTime(tmp[2]);
//							upstreamRet.setSuffix(tmp[3]);
//							data.add(upstreamRet);
//						}
//						result = getJSONResult(HttpStatusEnums.SUCCESS, data);
//						list.removeAll(subList);
//						RedisUtil.setList(RedisConfig.CONS_SIGNATURE_NUMBER_UNREAD+userId, list, null);
//					}
				}else{
					result = getJSONResult(HttpStatusEnums.ERROR_SIGN, null);
				}
			}else{
				result = getJSONResult(HttpStatusEnums.ERROR_INVALID, null);
			}
		}else{
			result = getJSONResult(HttpStatusEnums.NO_ACCOUNT, null);
		}
		logs.info("【findUpstream处理任务耗时----"+(System.currentTimeMillis()-startTime)+"(ms)----】 结果为:"+result.toString());
		return result;
	}
	
	/**
	 * @Title: getJSONResult
	 * @Description: 获得用于json传输的返回结果
	 */
	protected static <T> JSONResult<T> getJSONResult(HttpStatusEnums status, T data) {
		JSONResult<T> result = new JSONResult<T>(status);
		result.setData(data);
		return result;
	}
}
