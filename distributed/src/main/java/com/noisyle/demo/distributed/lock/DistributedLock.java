package com.noisyle.demo.distributed.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class DistributedLock {
	@Autowired
	private RedisTemplate<String, String> template;
	
	public boolean lock(String key, String value) {
		return template.boundValueOps(key).setIfAbsent(value);
	}
	public void unlock(String key, String value) {
		template.delete(key);
	}
}
