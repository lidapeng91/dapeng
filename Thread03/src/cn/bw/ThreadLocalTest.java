package cn.bw;

import java.util.HashMap;
import java.util.Random;

public class ThreadLocalTest {
	private static int result;
	HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
	ThreadLocal<Integer> threadLocal =new ThreadLocal<Integer>();
	public static void main(String[] args) {
		new ThreadLocalTest().test();
	}

	private void test() {
		final A a = new A();
		final B b = new B();
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					int result = new Random().nextInt();
					System.out.println("线程" + Thread.currentThread().getName() + "给result赋值" + result);
					a.set(result);
					b.set(result);
					a.get();
					b.get();
				}
			}).start();
		}
	}

	private class A {
		private int result;

		public void get() {
			System.out.println("A 从线程" + Thread.currentThread().getName() + "中获取的值是" + threadLocal.get());
		}

		public void set(int result) {
			threadLocal.set(result);
//			hashMap.put(Thread.currentThread().getName(), result);
//			this.result = result;
		}
	}

	private class B {
		private int result;

		public void get() {
			System.out.println("B 从线程" + Thread.currentThread().getName() + "中获取的值是" + threadLocal.get());
		}

		public void set(int result) {
			threadLocal.set(result);
//			hashMap.put(Thread.currentThread().getName(), result);
//			this.result = result;
		}
	}

	/*
	 * private class GetResult { private void A() { System.out.println("A 从线程" +
	 * Thread.currentThread().getName() + "中获取的值是" + result); }
	 * 
	 * private void B() { System.out.println("B 从线程" +
	 * Thread.currentThread().getName() + "中获取的值是" + result); } }
	 */
}
