package cn.bw;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test {

	private boolean flag = false;
	private Object data;
	ReentrantReadWriteLock re;

	public static void main(String[] args) {
//		new Test().getData();
		new Test().test();
	}

	private void test() {
		try {
			re.readLock().lock();
			System.out.println(Thread.currentThread().getName() + "Ö´ÐÐ");
			Thread.sleep(30000);
			re.readLock().unlock();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Object getData() {
		re.readLock().lock();
		if (!flag) {
			re.readLock().unlock();
			re.writeLock().lock();
			if (!flag) {
				data = "";
				flag = true;
			}
			re.writeLock().unlock();
			re.readLock().lock();

		}
		re.readLock().unlock();
		return null;
	}
}
