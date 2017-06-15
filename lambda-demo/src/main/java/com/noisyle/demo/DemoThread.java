package com.noisyle.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DemoThread extends Thread {
	InnerThread t;

	public void run() {
		System.out.println("Input \"exit\" to stop.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String cmd = null;
		do {
			System.out.println("OuterThread.run()");
			if (t == null) {
				t = new InnerThread();
				t.start();
			}
			try {
				cmd = br.readLine();
				if ("exit".equals(cmd)) {
					t.halt();
					break;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} while (true);
	}

	@Override
	public void destroy() {
		t.halt();
		super.destroy();
	}

	private class InnerThread extends Thread {
		private boolean running = true;

		public void run() {
			while (running) {
				System.out.println("InnerThread.run()");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		public void halt() {
			this.running = false;
		}
	}
	
	public static void main(String[] args) {
		DemoThread t = new DemoThread();
		t.start();
	}
}
