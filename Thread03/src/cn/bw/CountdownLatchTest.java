package cn.bw;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountdownLatchTest {
	static CountDownLatch sub = new CountDownLatch(2);
	static CountDownLatch main = new CountDownLatch(3);
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						sub.await();
						System.out.println("子线程"+Thread.currentThread().getName()+"准备执行");
						Thread.sleep(new Random().nextInt(10000));
						System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕。。");
						main.countDown();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		try {
			System.out.println("sub有"+sub.getCount()+"个计数器");;
			sub.countDown();
			System.out.println("sub还有"+sub.getCount()+"个计数器");;
			System.out.println("主线程唤醒子线程");
			main.await();
			System.out.println("主线程开始执行。。。。");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
