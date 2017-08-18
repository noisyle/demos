package com.noisyle.demo.distributed.lock;


public interface DistributedLock {
	boolean lock(String key, String value);
	void unlock(String key, String value);
}
