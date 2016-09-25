package cn.bw;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	static Semaphore semaphore = new Semaphore(3);

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(500);
						semaphore.acquire();
						System.out.println("线程"+Thread.currentThread().getName()+"拿到了灯，进来了，还剩 "+semaphore.availablePermits()+"个");
						semaphore.release();
						System.out.println("线程"+Thread.currentThread().getName()+"释放了信号灯，还剩 "+semaphore.availablePermits()+"个");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
