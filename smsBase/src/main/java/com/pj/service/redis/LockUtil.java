package com.pj.service.redis;

import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
 
import redis.clients.jedis.Jedis;
 

public class LockUtil {

	// 锁的有效时长(毫秒)
	protected static long lockExpires = 60000;

	// 阻塞式获取锁的实现
	public static Lock getuserLock(Jedis jedis, String username) {

		Lock lock = new RedisBasedDistributedLock(jedis, username + ".lock",
				lockExpires);

		return lock;
	}

}