package com.noisyle.demo.distributed.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestLock1 {
	// 为每个编码规则创建一个锁对象，保存在HashMap中，key为编码规则编号
	final static Map<String, Object> lockMap = new HashMap<String, Object>() {
		private static final long serialVersionUID = 1L;
		{
			this.put("0", new Object());
			this.put("1", new Object());
		}
	};

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new DemoThread1(i).start();
		}
	}

	public static Object getLock(int bizType) {
		// 根据业务类型找到对应的编码规则，并返回对应的锁对象
		String ruleNo = String.valueOf(bizType);
		return lockMap.get(ruleNo);
	}
}

class DemoThread1 extends Thread {
	static Random r = new Random();
	int id;
	int bizType;

	public DemoThread1(int id) {
		this.id = id;
		this.bizType = id % 2;
	}

	@Override
	public void run() {
		synchronized (TestLock1.getLock(bizType)) {
			System.out.println("id:\t" + id + " , bizType:\t" + bizType + " - start.");
			r.setSeed(id);
			try {
				sleep(r.nextInt(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("id:\t" + id + " , bizType:\t" + bizType + " - end.");
		}
	}

}
