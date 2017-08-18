package com.noisyle.demo.distributed.lock;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DistributedLockTemplate implements ApplicationContextAware {
	
	private static DistributedLock lock;

	public static boolean lock(String key, long timeout, LockCallback callbank) {
		checkDistributedLock();
		return lock.lock(key, "1");
	}

	public static void unlock(String key, long timeout, LockCallback callbank) {
		checkDistributedLock();
		lock.unlock(key, "1");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		lock = applicationContext.getBean(DistributedLock.class);
		checkDistributedLock();
	}
	
	private static void checkDistributedLock() {
		if (lock == null) {
			throw new IllegalStateException("DistributedLock did not initialized.");
		}
	}

}
