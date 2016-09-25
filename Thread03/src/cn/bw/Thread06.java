package cn.bw;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Thread06 {
	private static int count = 0;

	public static void main(String[] args) {
		/*
		 * ExecutorService threadPool = Executors.newFixedThreadPool(3); for
		 * (int i = 0; i < 10; i++) { threadPool.submit(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * System.out.println(Thread.currentThread().getName()); } }); }
		 */
		/*
		 * ExecutorService threadPool = Executors.newCachedThreadPool(); for
		 * (int i = 0; i < 20; i++) { threadPool.submit(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * System.out.println(Thread.currentThread().getName()); } }); }
		 */
		// ExecutorService threadPool = Executors.newSingleThreadExecutor();
		// ScheduledExecutorService threadPool =
		// Executors.newScheduledThreadPool(3);

		// for (int i = 0; i < 20; i++) {
		/*
		 * threadPool.scheduleAtFixedRate(new Runnable() {
		 * 
		 * @Override public void run() { // TODO Auto-generated method stub
		 * System.out.println(Thread.currentThread().getName()); } }, 2, 3,
		 * TimeUnit.SECONDS);
		 */
		// }
		/*
		 * ExecutorService threadPool = Executors.newCachedThreadPool();
		 * threadPool.execute(new Runnable() {
		 * 
		 * @Override public void run() { for (int i = 0; i < 20; i++) { // TODO
		 * Auto-generated method stub
		 * System.out.println(Thread.currentThread().getName()); } } });
		 */

		/*
		 * ExecutorService threadPool = Executors.newCachedThreadPool();
		 * Future<String> future = threadPool.submit(new Callable<String>() {
		 * 
		 * @Override public String call() throws Exception { // TODO
		 * Auto-generated method stub Thread.sleep(4000); return "123"; } });
		 * try { System.out.println(future.get(3, TimeUnit.SECONDS)); } catch
		 * (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		ExecutorCompletionService<Integer> service = new ExecutorCompletionService<Integer>(threadPool);
		Future<Integer> future = service.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// TODO Auto-generated method stub
				int result = 100;
				for (int i = 0; i < 10; i++) {
					result = i;
				}
				return result;

			}
		});

		try {
			for (int i = 0; i < 10; i++) {
				System.out.println(future.get());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
