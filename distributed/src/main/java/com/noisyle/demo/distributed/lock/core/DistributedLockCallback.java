package com.noisyle.demo.distributed.lock.core;


public interface DistributedLockCallback<T> {
	T onSuccess() throws InterruptedException;
	T onTimeout() throws InterruptedException;
}
