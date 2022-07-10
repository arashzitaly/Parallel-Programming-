package Labratory;

import java.math.BigInteger;

public class Lab2021_3_2_1 {

	public static void main(String[] args) {
        System.out.println(factorialNT(10,2));
    }
    public static BigInteger factorialS(int n){
        BigInteger res = BigInteger.valueOf(1);
        for(int i=1; i<=n; i++){
            res=res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
    public static BigInteger factorialRange(int n, int min, int max){
        BigInteger res = BigInteger.valueOf(1);
        for(int i=min; i<=max; i++){
            res = res.multiply(BigInteger.valueOf(i));
        }
        return res;
    }
    public static BigInteger factorial2T(int n){
        factorialRunnableBI t1 = new factorialRunnableBI(n,1,n/2);
        factorialRunnableBI t2 = new factorialRunnableBI(n,n/2+1,n);
        Thread th1 = new Thread(t1);
        Thread th2 = new Thread(t2);
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        }catch(InterruptedException e){}
        BigInteger lfactorial = t1.getResBI();
        BigInteger rfactorial = t2.getResBI();
        BigInteger total = lfactorial.multiply(rfactorial);
        return total;
    }
    public static BigInteger factorialNT(int n, int nt){
        int chunk = (int) Math.ceil((double) n/nt);
        factorialRunnableBI[] tasks = new factorialRunnableBI[nt];
        Thread[] threads = new Thread[nt];
        for (int i=0; i<nt; i++){
            tasks[i] = new factorialRunnableBI(n,chunk*i+1,Math.min((i+1)*chunk,n));
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }
        try {
            for(Thread thrds:threads){
                thrds.join();
            }
        }catch(InterruptedException e){}
        BigInteger total = BigInteger.valueOf(1);
        for (factorialRunnableBI tsks:tasks){
            total =total.multiply(tsks.getResBI());
        }
        return total;
    }
}
class factorialRunnableBI implements Runnable{
    int n, min, max;
    BigInteger res;
    factorialRunnableBI(int n, int min, int max){
        this.max=max;
        this.min=min;
        this.n=n;
    }
    BigInteger getResBI(){
        return res;
    }
    @Override
    public void run() {
        this.res=Lab2021_3_2_1.factorialRange(n,min,max);
    }
}
