package Labratory;

public class Turtle_Snail implements Runnable{

	public static void main(String[] args) {
//		Turtle_Snail task1 = new Turtle_Snail("snail", 1070, 1);
//		Turtle_Snail task2 = new Turtle_Snail("Turtle", 1, 38);
//		Thread th1 = new Thread(task1);
//		Thread th2 = new Thread(task2);
		
		Thread th1 = new Thread(new Turtle_Snail("snail", 1070, 1));
		Thread th2 = new Thread(new Turtle_Snail("Turtle", 1, 38));
		th1.start();
		th2.start();
	}
	
	String name;
	int position, speed;
	private static boolean Winner = false;
	
	public Turtle_Snail(String name, int position, int speed) {
		super();
		this.name = name;
		this.position = position;
		this.speed = speed;
	}
	
	public void run() {
		while (Winner == false) {
			position += speed;
			System.out.println(name + "is at position" + position);
			if (position >= 1100) {
				Winner = true;
				System.out.println("Winner is :" +name);
				break;
			}
		}
	}
	
	
}
