package com.noisyle.demo.distributed.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.noisyle.demo.distributed.config.AppConfig;
import com.noisyle.demo.distributed.lock.DistributedLockTemplate;
import com.noisyle.demo.distributed.lock.LockCallback;

public class TestLock {

    public static void main(String[] args) {
        boolean useLock = false;
        if (args.length > 0) {
            useLock = "lock".equals(args[0]) ? true : false;
        }
        if (useLock) {
			new AnnotationConfigApplicationContext(AppConfig.class);
        }
        int count = 20;
        List<DemoThread> threadList = new LinkedList<DemoThread>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            try {
                random.setSeed(i);
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            DemoThread t = new DemoThread(i, useLock);
            threadList.add(t);
            t.start();
        }
        while (threadList.size() > 0) {
            for (Iterator<DemoThread> i = threadList.iterator(); i.hasNext();) {
                DemoThread t = i.next();
                if (!t.running) {
                    i.remove();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count: " + DemoThread.count);
    }
}

class DemoThread extends Thread {
	final static private String KEY = "DEMO_KEY";
    public static int count = 0;
    public boolean running;
    private int tid;
    private boolean useLock;
    private static long timeout = 60000;
    private static Random random = new Random();

    public DemoThread(int tid, boolean useLock) {
        this.tid = tid;
        this.useLock = useLock;
        this.running = true;
    }

    @Override
    public void run() {
        if (useLock) {
            DistributedLockTemplate.execute(KEY, timeout, new LockCallback() {
            	@Override
            	public Object onSuccess() {
            		innerRun();
            		return null;
            	}
				@Override
				public Object onTimeout() {
					return null;
				}
			});
        } else {
        	innerRun();
        }
    }
    
    private void innerRun() {
    	System.out.println("Thread " + tid + ":\tstart, count=" + count);
    	int tmp = count;
    	try {
    		random.setSeed(tid);
    		sleep(random.nextInt(1000));
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    	tmp++;
    	count = tmp;
    	System.out.println("Thread " + tid + ":\tstoped, count=" + count);
    	running = false;
    }
}
