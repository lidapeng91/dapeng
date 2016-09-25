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
					System.out.println("�߳�" + Thread.currentThread().getName() + "��result��ֵ" + result);
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
			System.out.println("A ���߳�" + Thread.currentThread().getName() + "�л�ȡ��ֵ��" + threadLocal.get());
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
			System.out.println("B ���߳�" + Thread.currentThread().getName() + "�л�ȡ��ֵ��" + threadLocal.get());
		}

		public void set(int result) {
			threadLocal.set(result);
//			hashMap.put(Thread.currentThread().getName(), result);
//			this.result = result;
		}
	}

	/*
	 * private class GetResult { private void A() { System.out.println("A ���߳�" +
	 * Thread.currentThread().getName() + "�л�ȡ��ֵ��" + result); }
	 * 
	 * private void B() { System.out.println("B ���߳�" +
	 * Thread.currentThread().getName() + "�л�ȡ��ֵ��" + result); } }
	 */
}
