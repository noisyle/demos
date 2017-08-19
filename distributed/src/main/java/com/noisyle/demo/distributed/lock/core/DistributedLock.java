package com.noisyle.demo.distributed.lock.core;

public interface DistributedLock {
	boolean lock(DistributedLockObject lo, long timeout);
	void unlock(DistributedLockObject lo, long timeout);
}
