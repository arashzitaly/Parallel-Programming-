package Labratory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class LavLive6_3 {

	public static void main(String[] args) {
		
		CyclicBarrier barries = new CyclicBarrier(3);
		
		Meals task = new Meals(barries);
		Thread th1 = new Thread(task, "BurgerMachine");
		Thread th2 = new Thread(task, "FriesMachine");
		Thread th3 = new Thread(task, "ColaMachine");
		th1.start();
		th2.start();
		th3.start();
		
		
	}
}

class Meals implements Runnable {
	
	 CyclicBarrier barriers;

	public Meals(CyclicBarrier barriers) {
		super();
		this.barriers = barriers;
	}

	@Override
	public void run() {
		String thName = Thread.currentThread().getName();
		System.out.println("Machine: "+thName+ " is starting...");
		switch (thName) {
		case "BurgerMachine":
			System.out.println("Preparing burgers");
			break;
			
		case "FriesMachine":
			System.out.println("Prepering fries");
			break;
		case "ColaMachine":
			System.out.println("Preperin g cola");
			break;
		default:
			break;
		}
		
		try {
			this.barriers.await();
		} catch (BrokenBarrierException | InterruptedException e) {}
		  //catch(InterruptedException e) {}
		System.out.println(thName+" ... done!");
	}
	 
}