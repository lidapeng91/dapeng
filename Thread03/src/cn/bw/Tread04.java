package cn.bw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tread04 {
	private int num = 1;

	public static void main(String[] args) {
		new Tread04().test();
	}

	private void test() {

		Factory factory = new Factory();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 100000; i++) {
//					factory.prodcut();
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 70000; i++) {
//					factory.consume();
				}
			}
		}).start();
	}

	private class Factory {
		private boolean isShouldProduct = true;
		LinkedList<Car> list = new LinkedList<Car>();
		private ReentrantLock lock = new ReentrantLock();
		Condition condition1 = lock.newCondition();
		Condition condition2 = lock.newCondition();
		Condition condition3 = lock.newCondition();
		public void method1() {
			lock.lock();
			try {
				if (1 == list.size()) {
					try {
						System.out.println("������10������Ҫ�����ˡ�������");
						condition1.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Car car = new Car(num++);
				System.out.println("������ " + car);
				list.add(car);
				condition3.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

		}

		public void method2() {
			lock.lock();
			try {
				if (0 == list.size()) {
					try {
						System.out.println("�������ˣ���Ҫ�����ˡ�������");
						condition2.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("������ " + list.removeFirst());
				condition3.signal();
			} finally {
				lock.unlock();
			}
		}

		public void method3() {
			lock.lock();
			try {
				if (0 == list.size()) {
					try {
						System.out.println("�������ˣ���Ҫ�����ˡ�������");
						condition3.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("������ " + list.removeFirst());
				condition1.signal();
			} finally {
				lock.unlock();
			}

		}
		private class Car {
			private int carNum;

			public Car(int carNum) {
				this.carNum = carNum;
			}

			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "car num is " + carNum;
			}
		}
	}
}