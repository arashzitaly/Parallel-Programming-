package Labratory;

public class Lab_Live4 {

	public static void main(String[] args) {
		
		int n = 2;
		int[][] a = Lab_Live3_2.MatrixGenerator.GenMatrix(n);
		int[][] b = Lab_Live3_2.MatrixGenerator.GenMatrix(n);
		int[][] c = IntervalMM(a, b, 0, 2);
		int[][] c1 = ParallelMM(a, b, 2);
		
		//this print will work on the IntervalMM
		Lab_Live3_2.PrintMatrix.print(a);
		Lab_Live3_2.PrintMatrix.print(b);
		Lab_Live3_2.PrintMatrix.print(c);
		Lab_Live3_2.PrintMatrix.print(c1);
		
	}
	
	public static int[][] IntervalMM(int[][] a, int[][] b, int min, int max){
		int n = a.length;
		int[][] c = new int[n][n];
		
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < n; j++) {
				c[i][j] = 0;
				for (int k = 0; k < n; k++) {
					c[i][j] += a[i][j] * b[k][j];
				}
			}
		}
		return c;
	}
	
	public static int[][] Extricate(int[][] a, int min, int max){
		int n = a.length;
		int[][] result = new int[max-min][n];
		for (int i = 0; i < max-min; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = a[min+i][j];
			}
		}
		return result;
	}
	
	public static void PrintMatrixEX(int[][] a) {

		//we will work with every matrix not just quadratic matrix
		int row = a.length;
		int col = a[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.println(a[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static int[][] ParallelMM(int[][] a, int[][] b, int nt){
		
		int n = a.length;
		int chunck = (int) Math.ceil((double)n/nt);
		int[][] c = new int[n][n];
		MMRun[] task = new MMRun[nt];
		Thread[] threads = new Thread[nt];
		
		for (int i = 0; i < nt; i++) {
			task[i] = new MMRun(a, b, c, i+chunck, Math.min((i+1)*chunck, n));
			threads[i] = new Thread(task[i]);
			threads[i].start();
		}
		
		try {
			for (Thread th : threads) {
				th.join();
			}
		} catch (InterruptedException e) {}
		
		int[][] temp;
		for (int i = 0; i < nt; i++) {
			temp = Extricate(task[i].getC(), chunck*i, Math.min((i+1)*chunck, n));
			System.arraycopy(temp, 0,c ,chunck*i, temp.length);
		}
		return c;
	}
}

class MMRun implements Runnable{
	
	int[][] a,b,c;
	int min, max;
	public MMRun(int[][] a, int[][] b, int[][] c, int min, int max) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.min = min;
		this.max = max;
	}
	
	public int[][] getC() {
		return c;
	}

	@Override
	public void run() {
		this.c = Lab_Live4.IntervalMM(a, b, min, max);
	}
}