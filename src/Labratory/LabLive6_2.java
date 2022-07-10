package Labratory;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LabLive6_2 {
	
	public static int n = 1000;
	public static int[][] a = Lab_Live3_2.MatrixGenerator.GenMatrix(n);
	public static int[][] b = Lab_Live3_2.MatrixGenerator.GenMatrix(n);
	public static int[][] c = new int[n][n];
	public static int nt = n;

	public static void main(String[] args) {
		
		Date s1 = new Date();
		Lab_Live3_2.SMM.sqMM(a, b);
		Date e1 = new Date();
		Date s2 = new Date();
		MMthdpool(a, b, nt);
		Date e2 = new Date();
		     System.out.println("S: "+(e1.getTime()-s1.getTime())+" P: "+(e2.getTime()-s2.getTime()));
	}
	
	public static void MMthdpool(int[][] a, int[][] b, int nt) {
		ExecutorService threadPool = Executors.newFixedThreadPool(nt);
		for (int i = 0; i < nt; i++) {
			threadPoolMM task = new threadPoolMM(i,c);
			threadPool.execute(task);
		}
		threadPool.shutdown();
	}
}
class threadPoolMM implements Runnable {
	int index;
	int[][] result;
	int n = LabLive6_2.n;
	public threadPoolMM(int index, int[][] result) {
		super();
		this.index = index;
		this.result = result;
	}
	@Override
	public void run() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				result[index][i] += LabLive6_2.a[index][j]*LabLive6_2.b[j][i];
			}
		}
	}
	
	
}
