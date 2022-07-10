package Labratory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LabLive6 {

	public static void main(String[] args) {
		
		TimeInit task1 = new TimeInit();
		TimeInit task2 = new TimeInit();
		TimeInit task3 = new TimeInit();
		TimeInit task4 = new TimeInit();
		TimeInit task5 = new TimeInit();
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		threadPool.execute(task1);
		threadPool.execute(task2);
		threadPool.execute(task3);
		threadPool.execute(task4);
		threadPool.execute(task5);
		threadPool.shutdown();
	}
}

class TimeInit implements Runnable {

	@Override
	public void run() {

		try {
			for (int i = 0; i <= 1; i++) {
				//Initilization
				if (i == 0) {
					Date d = new Date();
					SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
					System.out.println("Initilization of "+Thread.currentThread().getName()+" "+df.format(d));
				}else {
					Date d = new Date();
					SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
					System.out.println("Time of execution "+Thread.currentThread().getName()+" "+df.format(d));
				}
				Thread.sleep(1000);
			}
			System.out.println(Thread.currentThread().getName()+" done!");
			
		} catch (InterruptedException e) {}
	}

}
