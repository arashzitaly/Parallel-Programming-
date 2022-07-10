package Labratory;

import java.util.concurrent.atomic.AtomicInteger;

public class Lab_Live5 {

	public static void main(String[] args) {
		IncrCounter task = new IncrCounter(10000);
		Thread th1 = new Thread(task);
		Thread th2 = new Thread(task);
		th1.start();
		th2.start();
		try {
			th1.join();
			th2.join();
		} catch (InterruptedException e) {}
		
		System.out.println("Overall value of counter is: "+task.getCounter());
	}

}

class IncrCounter implements Runnable{
//	public static AtomicInteger counter = new AtomicInteger();
	int level;
	int counter;
	
	public IncrCounter(int level) {
		super();
		this.level = level;
	}
	
	int getCounter() {
//		return counter.get();
		return counter;
	}


	@Override
	public void run() {

		for (int i = 1; i <= level; i++) {
//			counter.incrementAndGet();
			counter++;
		}
	}
	
}
