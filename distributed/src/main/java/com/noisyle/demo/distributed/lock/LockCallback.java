package com.noisyle.demo.distributed.lock;


public interface LockCallback {
	void onSuccess();
	void onTimeout();
}
