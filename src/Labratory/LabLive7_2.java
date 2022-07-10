package Labratory;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class LabLive7_2 {

	public static ArrayList<Integer> numbers = new ArrayList<Integer>();
	public static ArrayList<Integer> partial_result = new ArrayList<Integer>();

	public static void main(String[] args) {

		CountDownLatch barrier = new CountDownLatch(3);
		fillArrayList(30);
		new Thread(new sumRunnable(0, 10, barrier)).start();
		new Thread(new sumRunnable(10, 20, barrier)).start();
		new Thread(new sumRunnable(20, 30, barrier)).start();
		
		try {
			barrier.await();
		} catch (InterruptedException e) {}
		
		int finalSum = 0;
		for (int i = 0; i < partial_result.size(); i++) {
			try {
				finalSum += partial_result.get(i);
			} catch (Exception e) {}
		}
		System.out.println("Overall sum is "+finalSum);
	}

	public static void fillArrayList(int n) {
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			numbers.add(r.nextInt(10));
		}
	}
}

class sumRunnable implements Runnable {

	int start, end;
	CountDownLatch c;
	public sumRunnable(int start, int end, CountDownLatch b) {
		super();
		this.start = start;
		this.end = end;
		this.c = b;
	}
	@Override
	public void run() {
		int sum = 0;
		for (int i = start; i < end; i++) {
			sum += LabLive7_2.numbers.get(i);
		}
		LabLive7_2.partial_result.add(sum);
		System.out.println("Thread "+Thread.currentThread().getName()+" sum is "+sum);

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {}
		c.countDown();
	}

}
