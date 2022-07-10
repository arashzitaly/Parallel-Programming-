package Labratory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab_Live5_2 implements Runnable{
	
	public static AtomicInteger counter = new AtomicInteger();
	public static int level = 100;
	public static int thPoolSize = 5;
	static int counter1 = 0;
	
	public static void main(String[] args) {
		
		Lab_Live5_2 task = new Lab_Live5_2();
		ExecutorService threadPool = Executors.newFixedThreadPool(thPoolSize);
		for (int i = 0; i < thPoolSize; i++) {
			threadPool.submit(task);
		}
		threadPool.shutdown();
		
	}
	@Override
	public void run() {
//		while (counter.get() < level) {
			while(counter1 < level) {
			counter1++;
			System.out.println(Thread.currentThread().getName()+" "+counter1);
			
//			System.out.println(Thread.currentThread().getName()+" "+counter.incrementAndGet());
		}
		
	}

}
