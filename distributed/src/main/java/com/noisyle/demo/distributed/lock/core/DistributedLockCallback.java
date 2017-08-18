package com.noisyle.demo.distributed.lock.core;


public interface DistributedLockCallback {
	Object onSuccess() throws InterruptedException;
	Object onTimeout() throws InterruptedException;
}
