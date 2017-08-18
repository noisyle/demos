package com.noisyle.demo.distributed.lock.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.Jedis;

import com.noisyle.demo.distributed.lock.core.DistributedLock;
import com.noisyle.demo.distributed.lock.core.DistributedLockObject;

public class RedisDistributedLock implements DistributedLock {

	private RedisTemplate<String, String> template;
	
    final private static String luaLock = "local r = tonumber(redis.call('SETNX', KEYS[1], ARGV[1]));\n"
            + "redis.call('PEXPIRE', KEYS[1], ARGV[2]);\n"
            + "return r";
    
    final private static String luaUnlock = "local v = redis.call('GET', KEYS[1]);\n"
    		+ "if v == ARGV[1] then\n"
    		+ "redis.call('DEL',KEYS[1]);\n"
    		+ "end";

    public RedisDistributedLock(RedisTemplate<String, String> template) {
    	this.template = template;
	}
    
	public boolean lock(DistributedLockObject lo, long timeout) {
		long r = template.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				Jedis jedis = (Jedis) connection.getNativeConnection();
				return (Long) jedis.eval(luaLock, 1, new String[]{lo.getKey(), lo.getValue(), String.valueOf(timeout)});
			}
		});
		return r==1;
	}

	public void unlock(DistributedLockObject lo, long timeout) {
		template.execute(new RedisCallback<Long>() {
			@Override
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				Jedis jedis = (Jedis) connection.getNativeConnection();
				return (Long) jedis.eval(luaUnlock, 1, new String[]{lo.getKey(), lo.getValue()});
			}
		});
	}
}
