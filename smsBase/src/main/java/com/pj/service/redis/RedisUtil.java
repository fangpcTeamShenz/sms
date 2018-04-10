package com.pj.service.redis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.pj.service.mq.ObjectAndByte;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

/**
 * 共享缓存
 * @author Fangpc
 *
 */
public final class RedisUtil {
	
	protected static final Logger logs = Logger.getLogger(RedisUtil.class);
	
	// Redis服务器IP(只能阿里云内网访问)(商用地址)
	private static String ADDR = "r-uf6be3b5a4a8c044.redis.rds.aliyuncs.com";
	 
	//Redis服务器IP(只能阿里云内网访问)(测试地址)
//	private static String ADDR = "r-uf6632fbc1c73044.redis.rds.aliyuncs.com";
	
	// Redis的端口号
	private static int PORT = 6379;

	// 访问密码
	private static String AUTH = "A1b2c3d4";

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 8000;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 8000;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 5000;

	private static int TIMEOUT = 20000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	private static JedisPool jedisPool = null;
	
	/**
	 * 初始化Redis连接池
	 */
	static {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			//最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
			config.setMaxIdle(MAX_IDLE);
			//最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
    		config.setMaxTotal(MAX_ACTIVE);
//			config.setMaxWaitMillis(MAX_WAIT);
//			config.setTestOnBorrow(TEST_ON_BORROW);
       		config.setTestOnBorrow(false);
    		config.setTestOnReturn(false);
			jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取Jedis实例
	 * 
	 * @return
	 */
	public synchronized static Jedis getJedis() {
		try {
			if (jedisPool != null) {
				Jedis resource = jedisPool.getResource();
				return resource;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 释放jedis资源
	 * 释放redis连接资源    获取 redis连接资源后必须释放 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 获取list值
	 * @param key
	 * @param start(下标,从0开始)
	 * @param stop(下标,-1代表最后一个元素)
	 * @return 保证返回不为空
	 */
	public static List<String> getList(String key,Integer start ,Integer stop){
		Jedis jedis = RedisUtil.getJedis();
		List<String> retList = new ArrayList<>();
		try {
			if(start!=null && stop!=null){
				retList = jedis.lrange(key, start, stop);
			}else{
				String retStr = jedis.lpop("list");
				if(StringUtils.isNotBlank(retStr)){
					retList.add(retStr);
				}
			}
		} catch (Exception e) {
			logs.error("【getList】", e);
		}finally{
			returnResource(jedis);	
		}
		return retList;
	}
	
	/**
	 * 写入list值
	 * @param key
	 * @param list
	 * @param seconds : 实效时长,null永久
	 */
	public static void setList(String key,List<String> list,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.del(key);
			for (String obj : list) {
//				jedis.lpush(key, obj);
				jedis.rpush(key, obj);
				
			}
			if(seconds!=null){
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			logs.error("【setList】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 写入list值
	 * @param key
	 * @param list
	 * @param seconds : 实效时长,null永久
	 */
	public static void setListArr(Map<String, List<String>> temp,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
			Iterator<Map.Entry<String, List<String>>> entries = temp.entrySet().iterator(); 
			while (entries.hasNext()) {  
			    Map.Entry<String, List<String>> entry = entries.next();
			    jedis.del(entry.getKey());//写入前,先删除
			    List<String> set = entry.getValue();
				for (String obj : set) {
					jedis.lpush(entry.getKey(), obj);
				}
			    if(seconds!=null){
					jedis.expire(entry.getKey(), seconds);
				}
			}  
		} catch (Exception e) {
			logs.error("【setListArr】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 得到val对应的值
	 * @param key
	 */
	public static String getVal(String key){
		String retStr = null;
		Jedis jedis = RedisUtil.getJedis();
		try {
			retStr = jedis.get(key);
		} catch (Exception e) {
			logs.error("【getVal】", e);
		}finally{
			returnResource(jedis);	
		}
		return retStr;
	}
	
	/**
	 * 写入一个值
	 * @param key
	 * @param val
	 * @param seconds : 实效时长,单位秒,null永久
	 */
	public static void setVal(String key,String val,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.set(key,val);
			if(seconds!=null){
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			logs.error("【setVal】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 写入一个值(为了加快速度采用批处理)
	 * @param key
	 * @param val
	 * @param seconds : 实效时长,单位秒,null永久
	 */
	public static void setValArr(Map<String, String> temp,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
//			Iterator<Map.Entry<String, String>> entries = temp.entrySet().iterator(); 
//			while (entries.hasNext()) {  
//			    Map.Entry<String, String> entry = entries.next();
//			    jedis.del(entry.getKey());
//			    jedis.set(entry.getKey(),entry.getValue());
//			    if(seconds!=null){
//					jedis.expire(entry.getKey(), seconds);
//				}
//			}  
			
			Pipeline p = jedis.pipelined();
			Iterator<Map.Entry<String, String>> entries = temp.entrySet().iterator(); 
			while (entries.hasNext()) {  
			    Map.Entry<String, String> entry = entries.next();
				p.set(entry.getKey(),entry.getValue());
			    if(seconds!=null){
			    	p.expire(entry.getKey(), seconds);
				}
			}
			p.sync();
		} catch (Exception e) {
			logs.error("【setValArr】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 多个值写入
	 * @param keyAndVal(key和val一一对应的多个值,eg:key1,val1,key2,val2,key3,val3...)
	 */
	public static void setMval(String... keyAndVal){
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.mset(keyAndVal);
		} catch (Exception e) {
			logs.error("【setMval】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 多个值一起读取
	 * @param keys
	 * @return
	 */
	public static List<String> getMval(String... keys){
		List<String> retList = null;
		Jedis jedis = RedisUtil.getJedis();
		try {
			retList = jedis.mget(keys);
		} catch (Exception e) {
			logs.error("【getMval】", e);
		}finally{
			returnResource(jedis);	
		}
		return retList;
	}
	
	/**
	 *保存一个对象
	 * @param key
	 * @param obj
	 * @param seconds : 实效时长,单位秒,null永久
	 */
	public static void setObj(String key,Object obj,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.set(key.getBytes(), ObjectAndByte.toByteArray(obj));
			if(seconds!=null){
				jedis.expire(key.getBytes(), seconds);
			}
		} catch (Exception e) {
			logs.error("【setObj】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 *保存一个对象(采用批量提交)
	 * @param key
	 * @param obj
	 * @param seconds : 实效时长,单位秒,null永久
	 */
	public static void setObjArr(Map<String, Object> temp,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {			
//			Iterator<Map.Entry<String, Object>> entries = temp.entrySet().iterator(); 
//			while (entries.hasNext()) {  
//			    Map.Entry<String, Object> entry = entries.next();
//			    jedis.del(entry.getKey().getBytes());
//			    jedis.set(entry.getKey().getBytes(), ObjectAndByte.toByteArray(entry.getValue()));
//				if(seconds!=null){
//					jedis.expire(entry.getKey().getBytes(), seconds);
//				}
//			} 
			
			Pipeline p = jedis.pipelined();
			Iterator<Map.Entry<String, Object>> entries = temp.entrySet().iterator(); 
			while (entries.hasNext()) {  
			    Map.Entry<String, Object> entry = entries.next();
			    p.set(entry.getKey().getBytes(), ObjectAndByte.toByteArray(entry.getValue()));
				if(seconds!=null){
					p.expire(entry.getKey().getBytes(), seconds);
				}
			}
			p.sync();
		} catch (Exception e) {
			logs.error("【setObjArr】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 获取一个对象
	 * @param key
	 * @param cla
	 * @return
	 */
	public static Object getObj(String key){
		Jedis jedis = RedisUtil.getJedis();
		Object obj = null;
		try {
			byte[] bs = jedis.get(key.getBytes());
			if(bs!=null){
				obj = ObjectAndByte.toObject(bs);
			}
		} catch (Exception e) {
			logs.error("【getObj】", e);
		}finally{
			returnResource(jedis);	
		}
		return obj;
	}
	
	/**
	 *存入MAP
	 * @param key
	 * @param map
	 * @param seconds : 实效时长,单位秒,null永久
	 */
	public static void setMap(String key, Map<String,String> map,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.del(key);
			jedis.hmset(key, map);
			if(seconds!=null){
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			logs.error("【setMap】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 取出MAP(一次取一个值)
	 * @param key
	 * @param name
	 * @return
	 */
	public static String getMap(String key,String name){
		String ret = null;
		Jedis jedis = RedisUtil.getJedis();
		try {
			List list = jedis.hmget(key, name);
			if(!CollectionUtils.isEmpty(list)){
				if (list.get(0) != null) {
					ret = String.valueOf(list.get(0));
				}
			}
		} catch (Exception e) {
			logs.error("【getMap】", e);
		}finally{
			returnResource(jedis);	
		}
		return ret;
	}
	
	/**
	 * 取出MAP(一次取多个值)
	 * @param key
	 * @param name
	 * @return
	 */
	public static List<String> getMapVals(String key,String... names){
		Jedis jedis = RedisUtil.getJedis();
		List<String> list = null;
		try {
			 list = jedis.hmget(key, names);
		} catch (Exception e) {
			logs.error("【getMapVals】", e);
		}finally{
			returnResource(jedis);	
		}
		return list;
	}
	
	/**
	 * 写入有序的集合(linkedhashset)
	 * 权重从1开始
	 * @param key
	 * @param set
	 * @param seconds : 实效时长,单位秒,null永久
	 */
	public static void setSortedSet(String key,List<String> set,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.del(key);//写入前,先删除
			double weight = 1.0;//权重
			for (String str : set) {
				jedis.zadd(key, weight++, str);
			}
			jedis.zrange(key, 0, -1);//所有的值,按权重排序
			if(seconds!=null){
				jedis.expire(key, seconds);
			}
		} catch (Exception e) {
			logs.error("【setSortedSet】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 写入有序的集合(linkedhashset)
	 * 权重从1开始
	 * @param key
	 * @param set
	 * @param seconds : 实效时长,单位秒,null永久
	 */
	public static void setSortedSetArr(Map<String, List<String>> temp,Integer seconds){
		Jedis jedis = RedisUtil.getJedis();
		try {
//			Iterator<Map.Entry<String, List<String>>> entries = temp.entrySet().iterator(); 
//			while (entries.hasNext()) { 
//			    Map.Entry<String, List<String>> entry = entries.next();
//			    jedis.del(entry.getKey());//写入前,先删除
//			    double weight = 1.0;//权重
//			    List<String> set = entry.getValue();
//				for (String str : set) {
//					jedis.zadd(entry.getKey(), weight++, str);
//				}
//				jedis.zrange(entry.getKey(), 0, -1);//所有的值,按权重排序
//			    if(seconds!=null){
//					jedis.expire(entry.getKey(), seconds);
//				}
//			}  
			
			Pipeline p = jedis.pipelined();
			Iterator<Map.Entry<String, List<String>>> entries = temp.entrySet().iterator(); 
			while (entries.hasNext()) { 
			    Map.Entry<String, List<String>> entry = entries.next();
			    RedisUtil.del(entry.getKey());//写入前,先删除
			    double weight = 1.0;//权重
			    List<String> set = entry.getValue();
				for (String str : set) {
					p.zadd(entry.getKey(), weight++, str);
				}
				p.zrange(entry.getKey(), 0, -1);//所有的值,按权重排序
			    if(seconds!=null){
					p.expire(entry.getKey(), seconds);
				}
			} 
			p.sync();
		} catch (Exception e) {
			logs.error("【setSortedSetArr】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 获取有序的集合
	 * @param key
	 * @param start(下标0开始)
	 * @param end(全部为-1)
	 * @return
	 */
	public static Set<String> getSortedSet(String key,Long start ,Long end){
		Set<String> set = null;
		Jedis jedis = RedisUtil.getJedis();
		try {
			set = jedis.zrange(key, start, end);
		} catch (Exception e) {
			logs.error("【getSortedSet】", e);
		}finally{
			returnResource(jedis);	
		}
		return set;
	}
	
	/**
	 * 设置key的有效时长
	 * @param key
	 * @param seconds 有效秒
	 * @return
	 */
	public static Long setExpire(String key,int seconds){
		Long ret = 0L;
		Jedis jedis = RedisUtil.getJedis();
		try {
			ret = jedis.expire(key, seconds);
		} catch (Exception e) {
			logs.error("【setExpire】", e);
		}finally{
			returnResource(jedis);	
		}
		return ret;
	}
	
	/**
	 * 删除key对应的值
	 * @param key
	 */
	public static void del(String key){
		Jedis jedis = RedisUtil.getJedis();
		try {
			jedis.del(key);
		} catch (Exception e) {
			logs.error("【del】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	/**
	 * 批量删除key对应的值
	 * @param key
	 */
	public static void delArr(List<String> keys){
		Jedis jedis = RedisUtil.getJedis();
		try {
			for (String key : keys) {
				jedis.del(key);
			}
		} catch (Exception e) {
			logs.error("【del】", e);
		}finally{
			returnResource(jedis);	
		}
	}
	
	public static void main(String[] args) {
//		System.out.println(RedisUtil.getVal(RedisConfig.MER_AMOUNT_LIST + "1067" + "_" + "0"));
//		System.out.println(RedisUtil.getJedis().set(RedisConfig.MER_INFO_ACCOUNT + "1000", "699.670"));
//		System.out.println(RedisUtil.getVal(RedisConfig.MER_INFO + "1000"));
//		System.out.println(RedisUtil.getVal(RedisConfig.MER_INFO_ACCOUNT + "1000"));
//		Set<String> set = RedisUtil.getSortedSet("consRouteList_10095", 0L, -1L);
//		for (String string : set) {
//			System.out.println(string);
//		}
//		List<String> merIpList = RedisUtil.getList(RedisConfig.MER_IP_LIST+"1000", 0, -1);
//		merIpList.add("1948169142");
//		RedisUtil.setList(RedisConfig.MER_IP_LIST+"1000", merIpList, null);
//		for (String string : merIpList) {
//			System.out.println(string);
//		}
//		List<String> outOrderIdList = new ArrayList<>();
//		outOrderIdList.add("1");
//		outOrderIdList.add("2");
//		outOrderIdList.add("3");
//		RedisUtil.setList("test_list", outOrderIdList, 20);
//		List<String> aa = RedisUtil.getList("test_list", 0, -1);
//		for (String string : aa) {
//			System.out.println(string);
//		}
//		Map<String,String> map = new HashMap<String, String>();
//		map.put("timestatmp", "5678"+"");
//		map.put("code", "123456");//验证码
//		RedisUtil.setMap("123456789",map,null);
//		
//		System.out.println(RedisUtil.getMap("123456789", "timestatmp"));
		
//		List<Jedis> list = new ArrayList<Jedis>();
//		for (int i = 0; i < 400; i++) {
//			System.out.println("当前第"+i+"个连接");
//			list.add(RedisUtil.getJedis());
//		}
//		for (Jedis jedis : list) {
//			jedis.get("merIpList_10000");
//		}
//		System.out.println("rds结束");
	}

}
