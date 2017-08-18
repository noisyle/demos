package com.noisyle.demo.distributed.lock;


public interface LockCallback {
	Object onSuccess() throws InterruptedException;
	Object onTimeout() throws InterruptedException;
}
