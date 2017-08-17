package com.noisyle.demo.distributed.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

@Component
public class DistributedLock {
	@Autowired
	private RedisTemplate<String, String> template;
	
	final private static String timeout = "60000";
    final private static String luaGetLock = "local r = tonumber(redis.call('SETNX', KEYS[1], ARGV[1]));\n"
            + "redis.call('PEXPIRE', KEYS[1], ARGV[2]);\n"
            + "return r";
    final private static String luaReleaseLock = "redis.call('DEL', KEYS[1]);";

	public boolean lock(String key, String value) {
		long r = template.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				Jedis jedis = (Jedis) connection.getNativeConnection();
				return (Long) jedis.eval(luaGetLock, 1, new String[]{key, value, timeout});
			}
		});
		return r==1;
	}

	public void unlock(String key, String value) {
		template.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				Jedis jedis = (Jedis) connection.getNativeConnection();
				return (Long) jedis.eval(luaReleaseLock, 1, new String[]{key});
			}
		});
	}
}