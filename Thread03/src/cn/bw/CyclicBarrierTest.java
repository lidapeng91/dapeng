package cn.bw;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierTest {
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
	public static void main(String[] args) {
		for(int i=0;i<3;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						Thread.sleep(new Random().nextInt(10000));
						System.out.println("�߳�"+Thread.currentThread().getName()+"ִ����ϣ��ȴ��С���");
						cyclicBarrier.await();
						System.out.println("3���߳̽�������ִ������");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
