package Labratory;

public class Lab_Live4_2 {

	public static void main(String[] args) {
		CRun counter = new CRun(0);
		Thread th1 = new Thread(counter,"1st");
		Thread th2 = new Thread(counter,"2nd");
		Thread th3 = new Thread(counter,"3nd");
		th1.start();
		th2.start();
		th3.start();
		try {
			th1.join();
			th2.join();
			th3.join();
		} catch (InterruptedException e) {}

	}

}

class CRun implements Runnable{
	int counter;
	public CRun(int counter) {
		this.counter = counter;
	}

	public void inc() {
		counter++;
	}
	public void dec() {
		counter--;
	}
	public int getC() {
		return counter;
	}


	@Override
	synchronized public void run() {
		//synchronized (this) {
			this.inc();
			System.out.println("After increase:"+Thread.currentThread().getName()+" "+this.getC());
			this.dec();
			System.out.println("After deccrease:"+Thread.currentThread().getName()+" "+this.getC());
		}
	//}
}
