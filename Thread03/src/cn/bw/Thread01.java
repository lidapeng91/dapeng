package cn.bw;

import java.util.PrimitiveIterator;
import java.util.concurrent.locks.ReentrantLock;

public class Thread01 {
	ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {
		// new Thread01().test1();
		// new Thread01().test2();
		new Thread01().test();
	}

	private void test() {
		new Thread() {
			public void run() {
				while (true) {
					print("lidapeng");
				}
			};
		}.start();
		new Thread() {
			public void run() {
				while (true) {
					print("duanyangyang");
				}
			};
		}.start();
	}

	private void test1() {
		new Thread() {
			public void run() {
				while (true) {
					print("lidapeng");
				}
			};
		}.start();
	}

	private void test2() {
		new Thread() {
			public void run() {
				while (true) {
					print("duanyangyang");
				}
			};
		}.start();
	}

	public void print(String juzi) {
		lock.lock();
		try {
			for (int i = 0; i < juzi.length(); i++) {
				System.out.print(juzi.charAt(i));
			}
			System.out.println();
		} finally {
			lock.unlock();
		}

	}
}
