package com.noisyle.demo.distributed.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.noisyle.demo.distributed.lock.core.DistributedLock;
import com.noisyle.demo.distributed.lock.core.DistributedLockCallback;
import com.noisyle.demo.distributed.lock.core.DistributedLockObject;

@Component
public class DistributedLockTemplate implements ApplicationContextAware {
	final static private Logger logger = LoggerFactory.getLogger(DistributedLockTemplate.class);
	private static DistributedLock lock;
	private static ThreadLocal<DistributedLockObject> locks = new ThreadLocal<DistributedLockObject>();
	private static long retryInterval = 100;
	private static ReentrantLock reentrantLock = new ReentrantLock();

	public static <T> T execute(String key, long timeout, DistributedLockCallback<T> callback) {
		checkDistributedLock();
		boolean hasLock = false;
		try {
			if (lock(key, timeout)) {
				hasLock = true;
				return callback.onSuccess();
			} else {
				return callback.onTimeout();
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (hasLock) {
				unlock(key, timeout);
			}
		}
		return null;
	}

	private static boolean lock(String key, long timeout) {
		DistributedLockObject lo = getLockObject(key);
		long expireAt = System.currentTimeMillis() + timeout;
		logger.debug("{}", reentrantLock);
		reentrantLock.lock();
		Condition con = reentrantLock.newCondition();
		try {
			while (System.currentTimeMillis() < expireAt) {
				if (lock.lock(lo, timeout)) {
					return true;
				} else {
					con.await(retryInterval, TimeUnit.MILLISECONDS);
				}
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			Thread.interrupted();
		} finally {
			reentrantLock.unlock();
		}
		return false;
	}

	private static void unlock(String key, long timeout) {
		DistributedLockObject lo = getLockObject(key);
		lock.unlock(lo, timeout);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		lock = applicationContext.getBean(DistributedLock.class);
		logger.debug("DistributedLock: {}", lock);
		checkDistributedLock();
	}

	private static void checkDistributedLock() {
		if (lock == null) {
			throw new IllegalStateException("DistributedLock did not initialized.");
		}
	}

	private static DistributedLockObject getLockObject(String key) {
		DistributedLockObject lo = locks.get();
		if (lo == null) {
			lo = new DistributedLockObject();
			lo.setKey(key);
			lo.setValue(key + "_" + Thread.currentThread().getId());
			locks.set(lo);
		}
		return lo;
	}
}
