package com.pj.service.redis;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
 
/**
 * 模拟ID生成 
 * @author lixiaohui
 *
 */
public class IDGenerator {

	private static BigInteger id = BigInteger.valueOf(0);

	private final Lock lock;
	private final Jedis jedis1;
	private static final BigInteger INCREMENT = BigInteger.valueOf(1);

	public IDGenerator(Lock lock, Jedis jedis1) {
		this.lock = lock;
		this.jedis1 = jedis1;
	}

	public String getAndIncrement() {
		if (lock.tryLock(3, TimeUnit.SECONDS)) {
			try {
				// TODO 这里获取到锁, 访问临界区资源
				// 获取redis连接资源
				Jedis jedis1 = RedisUtil.getJedis();

				int i = Integer.parseInt(jedis1.get("test"));
				i++;
				jedis1.set("test", i + "");

				String re = jedis1.get("test");
				// 释放redis连接资源
				RedisUtil.returnResource(jedis1);
				return re;
			} finally {
				lock.unlock();
			}
		}
		return null;
		// return getAndIncrement0;
	}

	private String getAndIncrement0() {
		String s = id.toString();
		id = id.add(INCREMENT);
		return s;
	}
}