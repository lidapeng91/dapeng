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
						System.out.println("���߳�"+Thread.currentThread().getName()+"׼��ִ��");
						Thread.sleep(new Random().nextInt(10000));
						System.out.println("���߳�"+Thread.currentThread().getName()+"ִ����ϡ���");
						main.countDown();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		try {
			System.out.println("sub��"+sub.getCount()+"��������");;
			sub.countDown();
			System.out.println("sub����"+sub.getCount()+"��������");;
			System.out.println("���̻߳������߳�");
			main.await();
			System.out.println("���߳̿�ʼִ�С�������");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
