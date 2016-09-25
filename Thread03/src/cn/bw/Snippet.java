package cn.bw;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Snippet {
	public static void main(String[] args) {

		try {
			ExecutorService service = Executors.newFixedThreadPool(10);
			CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(service);
			for (int i = 0; i < 10; i++) {
				final Integer seq = i + 1;
				completionService.submit(new Callable<Integer>() {

					public Integer call() throws Exception {
						try {
							Thread.sleep((long) (Math.random() * 1000));
						} catch (Exception e) {
						}
						return seq;
					}

				});
			}

			for (int i = 0; i < 10; i++) {
				Future<Integer> f = completionService.take();
				System.out.println(f.get());
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
