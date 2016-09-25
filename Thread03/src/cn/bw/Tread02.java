package cn.bw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Tread02 {
	private int num = 1;

	public static void main(String[] args) {
		new Tread02().test();
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

		public synchronized void prodcut() {
			if (10 == list.size()) {
				try {
					System.out.println("������10������Ҫ�����ˡ�������");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Car car = new Car(num++);
			System.out.println("������ " + car);
			list.add(car);
			notify();

		}

		public synchronized void consume() {
			if (0 == list.size()) {
				try {
					System.out.println("�������ˣ���Ҫ�����ˡ�������");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("������ " + list.removeFirst());
			notify();
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
