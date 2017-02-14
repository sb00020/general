/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomthreadpool;

/**
 *
 * @author brettsa
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RandomThreadpool {

    public static void main(String[] args) {

        Callabletest ct = new Callabletest();
        ct.callable();
        
    }

    public void testThreadpoolorig() {
        long startTime = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new Worker("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");

        long endTime = System.currentTimeMillis();
        long totalTimeLinear = endTime - startTime;

        startTime = System.currentTimeMillis();

        executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            Runnable worker = new Worker("" + i);
            executor.execute(worker);
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }

        endTime = System.currentTimeMillis();
        long totalTimeParallel = endTime - startTime;
        System.out.println("Finished all threads");

        System.out.println(totalTimeLinear);
        System.out.println(totalTimeParallel);
    }

}
