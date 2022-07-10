package Labratory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LaLive7 {

	static int[][] graph = {{0,3,99,5},{2,0,99,4},{99,1,0,5},{99,99,2,0}};
	static int n = graph.length;
	
	public static void main(String[] args) {
		
		
//		Lab_Live3_2.PrintMatrix.print(floyd(graph));
		ExecutorService ex = Executors.newFixedThreadPool(n);
		for (int i = 0; i < n; i++) {
			fwrRunnable task = new fwrRunnable(i);
			ex.execute(task);
		}
		ex.shutdown();
		Lab_Live3_2.PrintMatrix.print(graph);
	}
	
	public static int[][] floyd(int[][] graph1) {
		
		int n = graph1.length;
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < graph.length; j++) {
					graph1[i][j] = Math.min(graph1[i][j], graph1[i][k]+graph1[k][j]);
				}
			}
		}
		return graph1;
	}
}

class fwrRunnable implements Runnable {
	
	int index;
	int[][] matrix = LaLive7.graph;
	public fwrRunnable(int index) {
		super();
		this.index = index;
	}
	@Override
	public void run() {
		for (int k = 0; k < matrix.length; k++) {
			for (int i = 0; i < matrix.length; i++) {
				matrix[index][i] = Math.min(matrix[index][i], matrix[index][k] + matrix[k][i]);
			}
		}
	}
	
	
}
