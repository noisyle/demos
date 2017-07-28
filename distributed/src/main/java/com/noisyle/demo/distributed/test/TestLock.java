package com.noisyle.demo.distributed.test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.noisyle.demo.distributed.config.AppConfig;
import com.noisyle.demo.distributed.lock.DistributedLock;

public class TestLock {

    public static void main(String[] args) {
        boolean useLock = false;
        if (args.length > 0) {
            useLock = "lock".equals(args[0]) ? true : false;
        }
        AbstractApplicationContext context = null;
        DistributedLock lock = null;
        if (useLock) {
            context = new AnnotationConfigApplicationContext(AppConfig.class);
            lock = (DistributedLock) context.getBean("distributedLock");
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
            DemoThread t = new DemoThread(i, lock);
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
        if (useLock) {
            context.stop();
            context = null;
        }
        System.out.println("count: " + DemoThread.count);
    }
}

class DemoThread extends Thread {
    public static int count = 0;
    public boolean running;
    private int tid;
    private DistributedLock lock;
    private static Random random = new Random();

    public DemoThread(int tid, DistributedLock lock) {
        this.tid = tid;
        this.lock = lock;
        this.running = true;
    }

    @Override
    public void run() {
        if (lock != null) {
            while (!lock.lock("test", String.valueOf(tid))) {
                try {
                    System.out.println("Thread " + tid + " waiting for lock...");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        if (lock != null) {
            lock.unlock("test", String.valueOf(tid));
        }
    }
}
