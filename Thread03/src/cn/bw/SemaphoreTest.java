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
						System.out.println("�߳�"+Thread.currentThread().getName()+"�õ��˵ƣ������ˣ���ʣ "+semaphore.availablePermits()+"��");
						semaphore.release();
						System.out.println("�߳�"+Thread.currentThread().getName()+"�ͷ����źŵƣ���ʣ "+semaphore.availablePermits()+"��");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
