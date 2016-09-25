package cn.bw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tread03 {
	private int num = 1;

	public static void main(String[] args) {
		new Tread03().test();
	}

	private void test() {

		Factory factory = new Factory();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 100000; i++) {
					factory.prodcut();
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < 70000; i++) {
					factory.consume();
				}
			}
		}).start();
	}

	private class Factory {
		private boolean isShouldProduct = true;
		LinkedList<Car> list = new LinkedList<Car>();
		private ReentrantLock lock = new ReentrantLock();
		Condition condition = lock.newCondition();

		public void prodcut() {
			lock.lock();
			try {
				if (10 == list.size()) {
					try {
						System.out.println("生产了10个，需要消费了。。。。");
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				Car car = new Car(num++);
				System.out.println("生产了 " + car);
				list.add(car);
				condition.signal();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}

		}

		public void consume() {
			lock.lock();
			try {
				if (0 == list.size()) {
					try {
						System.out.println("消费完了，需要生产了。。。。");
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("消费了 " + list.removeFirst());
				condition.signal();
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