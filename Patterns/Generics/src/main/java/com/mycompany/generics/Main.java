/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generics;

import java.util.ArrayList;
import java.util.Collections;
import randomthreadpool.OutputMessage;

/**
 *
 * @author brettsa
 */
public class Main {

    public static void main(String[] args) throws Exception {
        OutputMessage o1 = new OutputMessage();
        long start = System.currentTimeMillis();
        ConcreteThreadPool pool = new ConcreteThreadPool(100, 8);

        ArrayList<OutputMessage> outputs = (ArrayList<OutputMessage>) pool.execute(pool.createCallables());

        System.out.println("Finished: " + outputs + "\n");

        Collections.sort(outputs);
        
        for (OutputMessage o : outputs) {

            System.out.println(o.getThread());
//            Double[] dd = new Double[o.getList().length];
//            for (int i = 0; i < o.getList().length; i++) {
//                dd[i] = o.getList()[i];
//            }
//            System.out.println("Thread : " + o.getThread() + " Number: " + Sorter.returnArrayAsString(dd));

        }
        
                    long stop = System.currentTimeMillis();

            System.out.println( (double) (stop - start) / 1000.);

    }

    public void splitThread() {
        int num = 64;

        for (int j = 0; j < 10; j++) {

            long start = System.currentTimeMillis();

            SplitThreadPool atp = new SplitThreadPool(100, num);

            ArrayList<OutputMessage> outputs = (ArrayList<OutputMessage>) atp.execute(atp.createCallables());

            for (OutputMessage o : outputs) {
                Double[] dd = new Double[o.getList().length];
                for (int i = 0; i < o.getList().length; i++) {
                    dd[i] = o.getList()[i];
                }
                //    System.out.println("Thread : " + o.getThread() + " Number: " + Sorter.returnArrayAsString(dd));

            }

            long stop = System.currentTimeMillis();

            System.out.println(num + " , " + (double) (stop - start) / 1000.);

            num *= 2;
        }
    }

    public void old() {

        //        Sorter.run();
//        
//        MaxOfThree.run();
//        
//        Box.run();
        // Simple example using arrays of integers and having them printed to concatenated string
//        ArrayList<Integer[]> input = new ArrayList<>();
//        input.add(new Integer[]{1, 2, 3, 4, 5});
//        input.add(new Integer[]{5, 4, 5, 6, 7});
//        input.add(new Integer[]{9, 8, 7, 6, 5});
//
//        String s = IOGeneric.run(input);
//
//        System.out.println(s);
//
//        // Array List in and out
//        
//        ArrayList<String> stringInput = new ArrayList<>();
//        stringInput.add("Apple");
//        stringInput.add("Pear");
//        stringInput.add("Banana");
//
//        Object o = IOGeneric.run(stringInput.toArray());
//
//        ArrayList<String> j = (ArrayList<String>) o;
//
//        System.out.println(j);
//        RandomNumberSeeding r = new RandomNumberSeeding(new InputMessage(8,8));
//        Object o = c.run(r);
//        OutputMessage output = (OutputMessage) o;
//        
//        System.out.println(output.getList().length);
    }



}
