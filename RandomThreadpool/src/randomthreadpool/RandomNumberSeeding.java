/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomthreadpool;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 *
 * @author brettsa
 */
public class RandomNumberSeeding implements Callable {

    private Random rnd;// = new Random();
    private final int seed, thread;

    public RandomNumberSeeding(InputMessage input) {
        thread = input.getThread();
        this.seed = input.getSeed();

    }

    public int getSeed() {
        return seed;
    }

    @Override
    public Object call() throws Exception {
        rnd = new Random(seed);

        double[] list = new double[1];

        for (int i = 0; i < list.length; i++) {

            double d = rnd.nextDouble();
            list[i] = d;

        }
        
        Thread.sleep(1000);
        return new OutputMessage(list, thread);

    }
}
