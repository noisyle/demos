package com.noisyle.demo.distributed.lock;


public interface DistributedLock {
	boolean lock(LockObject lo, long timeout);
	void unlock(LockObject lo, long timeout);
}
