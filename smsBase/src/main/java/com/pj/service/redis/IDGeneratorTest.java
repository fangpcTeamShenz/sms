package com.pj.service.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
 

 
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

 
public class IDGeneratorTest {

	private static Set<String> generatedIds = new HashSet<String>();

	private static final String LOCK_KEY = "lock.lock";
	private static final long LOCK_EXPIRE = 5 * 1000;
	// Redis服务器IP
	private static String ADDR = "139.224.133.126";

	// Redis的端口号
	private static int PORT = 6379;

	// 访问密码
	private static String AUTH = "RedisAly666";

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = 1024;

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = 200;

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = 10000;

	private static int TIMEOUT = 10000;

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = true;

	@Test
	public void test() throws InterruptedException {

		JedisPoolConfig config = new JedisPoolConfig();
		// 最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
		config.setMaxIdle(200);
		// 最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
		config.setMaxTotal(300);
		config.setTestOnBorrow(false);
		config.setTestOnReturn(false);
		JedisPool jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
		Jedis jedis1 = jedisPool.getResource();
		Lock lock1 = new RedisBasedDistributedLock(jedis1, LOCK_KEY,
				LOCK_EXPIRE);
		IDGenerator g1 = new IDGenerator(lock1, jedis1);
		IDConsumeMission consume1 = new IDConsumeMission(g1, "consume1");

		Jedis jedis2 = jedisPool.getResource();
		Lock lock2 = new RedisBasedDistributedLock(jedis2, LOCK_KEY,
				LOCK_EXPIRE);
		IDGenerator g2 = new IDGenerator(lock2, jedis1);
		IDConsumeMission consume2 = new IDConsumeMission(g2, "consume2");

		Thread t1 = new Thread(consume1);
		Thread t2 = new Thread(consume2);
		t1.start();
		t2.start();

		Thread.sleep(20 * 1000); // 让两个线程跑20秒

		IDConsumeMission.stop();

		t1.join();
		t2.join();
	}

	static String time() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	static class IDConsumeMission implements Runnable {

		private IDGenerator idGenerator;

		private String name;

		private static volatile boolean stop;

		public IDConsumeMission(IDGenerator idGenerator, String name) {
			this.idGenerator = idGenerator;
			this.name = name;
		}

		public static void stop() {
			stop = true;
		}

		public void run() {
			System.out.println(time() + ": consume " + name + " start ");
			while (!stop) {
				String id = idGenerator.getAndIncrement();
				if (generatedIds.contains(id)) {
					System.out.println(time()
							+ ": duplicate id generated, id = " + id);
					stop = true;
					continue;
				}

				generatedIds.add(id);
				System.out.println(time() + ": consume " + name + " add id = "
						+ id);
			}
			System.out.println(time() + ": consume " + name + " done ");
		}

	}

}