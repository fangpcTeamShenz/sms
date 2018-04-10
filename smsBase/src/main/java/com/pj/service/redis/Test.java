package com.pj.service.redis;


import redis.clients.jedis.Jedis;

public class Test {


    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		try {
//			String host = "r-uf6be3b5a4a8c044.redis.rds.aliyuncs.com";// 控制台显示访问地址
//			int port = 6379;
//			Jedis jedis = new Jedis(host, port);
//			// 鉴权信息
//			jedis.auth("A1b2c3d4");// password
//			String key = "redis";
//			String value = "aliyun-redis";
//			// select db默认为0
//			jedis.select(1);
//			// set一个key
//			jedis.set(key, value);
//			System.out.println("Set Key " + key + " Value: " + value);
//			// get 设置进去的key
//			String getvalue = jedis.get(key);
//			System.out.println("Get Key " + key + " ReturnValue: " + getvalue);
//			jedis.quit();
//			jedis.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}	
		
		 //获取redis连接资源 
//		 Jedis  jedis1=RedisUtil.getJedis();
//		try {
//			
//			 //设置test对象的值   如果test   对象存在  覆盖test  如果不存在 创建  
//			jedis1.set("test", "10086");
//			//得到 key  为  test的值  
//			System.out.println(jedis1.get("test"));
//			
//			jedis1.lpush("list", "aa");
//			jedis1.lpush("list", "bb");
//			jedis1.lpush("list", "cc");
//			jedis1.lpush("list", "dd");
//			System.out.println(jedis1.lrange("list", 0, -1));
//			System.out.println(jedis1.lrange("list", 0, 1));
//			System.out.println(jedis1.lpop("list")); // 栈顶
//			jedis1.del("list");
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}finally{
//			//释放redis连接资源    获取 redis连接资源后必须释放 
//			RedisUtil.returnResource(jedis1);	
//		}
	
		// for(int i=0;i<100;i++){
		// String username="test";
		// Jedis jedis=RedisUtil.getJedis();
		// //获取用户锁
		// Lock lock=LockUtil.getuserLock(jedis, username);
		// try {
		// // 判断用户是否存在锁 前面是时间长度后面是时间类型 此处时间是最长获取锁的等待时间
		// if (lock.tryLock(10, TimeUnit.SECONDS)) {
		// try {
		// // TODO 这里获取到锁, 执行业务操作
		//
		// System.out.println(jedis.get("test"));
		//
		// } finally {
		// // 释放锁
		// lock.unlock();
		//
		// }
		// } else {
		// System.out.println("获取锁失败");
		// }
		// } catch (Exception e) {
		// // TODO: handle exception
		// }finally{
		// //释放redis连接资源 获取 redis连接资源后必须释放
		// RedisUtil.returnResource(jedis);
		// }
		// }
		
	}
		
}


