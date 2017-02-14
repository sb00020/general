/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generics;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import randomthreadpool.Callabletest;
import randomthreadpool.InputMessage;
import randomthreadpool.OutputMessage;
import randomthreadpool.RandomNumberSeeding;

/**
 *
 * @author brettsa
 */
public class IOGeneric<M> {

    // generic method printArray
    public static <E> String printArray(E[] inputArray) {
        // Display array elements
        String s = "";

        for (E element : inputArray) {
            //System.out.printf("%s ", element);
            s += element + " ";
        }
        System.out.println();
        return s;
    }

    public static <I, O> O printArrayE(I[] inputArray) {
        // Display array elements
        String s = "";

        for (I element : inputArray) {
            //System.out.printf("%s ", element);
            s += element + " ";
        }
        System.out.println();

        O o = (O) s;
        return o;
    }
    
    private Class<M> clazzOfM;
       M createContents(Class<M> clazz) throws Exception{
        return clazz.newInstance();
    }
    
    public static <I, M, O> ArrayList<O> callables(ArrayList<I> inputArray, M method, int numThreads, int threadpoolSize) {

        final ExecutorService service;

        //ArrayList<OutputMessage> outputs = new ArrayList<>();
        ArrayList<O> genericOutputs = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            //genericOutputs.add(i, new O());
        }

        service = Executors.newFixedThreadPool(threadpoolSize);
        Set<Future<O>> set = new HashSet<>();

        for (int i = 0; i < numThreads; i++) {
            //InputMessage iMess = new InputMessage(i, i + 2);
            Callable<O> callable =null ; //= new M(inputArray.get(i));
            Future<O> future = service.submit(callable);
            set.add(future);
        }

        return new ArrayList<O>();
    }

    public static <I> String run(ArrayList<I[]> inputs) {
        String output = "";
        for (I[] i : inputs) {

            // Integer[] intArray = {1, 2, 3, 4, 5};
            output += printArray(i);

        }
        return output;
    }

    public static <I, O> ArrayList<O> run(I[] inputs) {
        String output = "";

        output += printArray(inputs);

        ArrayList<O> outputs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {

            outputs.add(printArrayE(inputs));
        }
        return outputs;
    }

    public static <I, O> ArrayList<O> callable(I[] inputs) {

        final ExecutorService service;

        int numThreads = 200;
        int threadpoolSize = 64;

        //ArrayList<OutputMessage> outputs = new ArrayList<>();
        ArrayList<O> genericOutputs = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            //outputs.add(i, new OutputMessage());
        }

        service = Executors.newFixedThreadPool(threadpoolSize);
        Set<Future<O>> set = new HashSet<>();

        for (int i = 0; i < numThreads; i++) {
            InputMessage iMess = new InputMessage(i, i + 2);
            Callable<O> callable = new RandomNumberSeeding(iMess);
            Future<O> future = service.submit(callable);
            set.add(future);
        }

    /*    set.stream().forEach((out) -> {
            try {
                int thread = out.get().getThread();
                double[] list = out.get().getList();

                genericOutputs.set(thread, new OutputMessage(list, thread));

                for (double d : list) {
                    //    System.out.println("thread " + thread + " double: " + d);
                }

            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Callabletest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });*/
        service.shutdownNow();

        return genericOutputs;
    }

}
