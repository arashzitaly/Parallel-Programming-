package Labratory;

import java.util.Date;
import java.util.Random;

import Labratory.Lab_Live3.MatrixRunnable;

public class Lab_Live3_2 {

    public static void main(String[] args) {
        int n = 1000;
        int[][] a = MatrixGenerator.GenMatrix(n);
        int[][] b = MatrixGenerator.GenMatrix(n);
//		PrintMatrix.print(a);
//		PrintMatrix.print(b);
        Date s1 = new Date();
        int[][] c1 = MatrixParallel.PMM(a, b);
        Date e1 = new Date();
        Date s2 = new Date();
        int[][] c2 = SMM.sqMM(a, b);
        Date e2 = new Date();
//		PrintMatrix.print(c);
        System.out.println("Parallel :" + (e1.getTime() - s1.getTime()) + " Sequantial :" + (e2.getTime() - s2.getTime()));

    }


    static class MatrixGenerator {
        public static int[][] GenMatrix(int n) {
            int[][] result = new int[n][n];
            Random rand = new Random();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = rand.nextInt(10);
                }
            }
            return result;
        }
    }

    static class PrintMatrix {
        public static void print(int[][] a) {
            int n = a.length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print(a[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    static class SMM {
    	
        public static int[][] sqMM(int[][] a, int[][] b) {
            int n = a.length;
            int[][] result = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = 0;
                    for (int j2 = 0; j2 < n; j2++) {
                        //this line is not correct, and because of this parallel will be always slower, which make no sense
                        //result[i][j] += a[i][j] + b[i][j];
                        //should be
                        result[i][j] += a[i][j2]*b[j2][j];
                    }
                }
            }
            return result;
        }
    }
}
//this must be as separate class (since it is runnable interface - different class)
class MatrixRunnable1 implements Runnable{
        int[][] a,b,c;
        int row;

        //Constructor
        public MatrixRunnable1(int[][] a, int[][] b, int[][] c, int row) {
            super();
            this.a = a;
            this.b = b;
            this.c = c;
            this.row = row;
        }

        @Override
        public void run() {
            for (int i = 0; i < a.length; i++) {
                c[row][i] = 0;
                for (int k = 0; k < a.length; k++) {
                    c[row][i] += a[row][k] + b[k][i];
                }
            }
        }
    }
//class below this comment can be in LabLive class as method (public static int[][] PMM ...) or use it as it is over here as separated class    
class MatrixParallel{
        public static int[][] PMM(int[][] a, int[][] b){
            int n = a.length;
            int[][] result = new int[n][n];
            MatrixRunnable[] task = new MatrixRunnable[n];
            Thread[] threads = new Thread[n];
            for (int i = 0; i < n; i++) {
                task[i] = new MatrixRunnable(a,b,result,i);
                threads[i] = new Thread(task[i]);
                threads[i].start();
            }

            try {
                for (Thread th : threads) {
                    th.join();
                }
            } catch (InterruptedException e) {}

            return result;
        }
    }

