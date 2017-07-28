package com.noisyle.demo.distributed.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.noisyle.demo.distributed.config.AppConfig;
import com.noisyle.demo.distributed.lock.DistributedLock;

public class TestWithoutLock {
	public static boolean useLock = false;

	public static void main(String[] args) {
		if (args.length > 0) {
			useLock = "uselock".equals(args[0]) ? true : false;
		}
		int count = 10;
		List<DemoThread> threadList = new LinkedList<DemoThread>();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			try {
				random.setSeed(i);
				Thread.sleep(random.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DemoThread t = new DemoThread(i);
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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("count: " + DemoThread.count);
	}
}

class DemoThread extends Thread {
	public static int count = 0;
	public boolean running;
	private int tid;
	private static Random random = new Random();
	private static DistributedLock lock;

	public DemoThread(int tid) {
		this.tid = tid;
		this.running = true;
		if (TestWithoutLock.useLock && lock == null) {
			synchronized (DemoThread.class) {
				if (lock == null) {
					ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
					lock = (DistributedLock) context.getBean("distributedLock");
				}
			}
		}
	}

	@Override
	public void run() {
		if (TestWithoutLock.useLock) {
			while(!lock.lock("test", String.valueOf(tid))){
			}
		}
		System.out.println("Thread " + tid + " start, count=" + count);
		int tmp = count;
		try {
			random.setSeed(tid);
			sleep(random.nextInt(1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		tmp++;
		count = tmp;
		System.out.println("Thread " + tid + " stoped, count=" + count);
		running = false;
		if (TestWithoutLock.useLock) {
			lock.unlock("test", String.valueOf(tid));
		}
	}
}
