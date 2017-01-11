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
 * @author Sam
 */
public class SplitThreadPool<O> {

    private final int numThreads;
    private final int numExecutors;
    final ExecutorService service;
    Set<Future<OutputMessage>> set = new HashSet<>();
    //Callable<OutputMessage> callable;
    ArrayList<O> outputs = new ArrayList<>();

    public <I, O> SplitThreadPool(int t, int e) {

        numThreads = t;
        numExecutors = e;
        
        for (int i = 0; i < numThreads; i++) {
            outputs.add(i, null);
        }
        service = Executors.newFixedThreadPool(numExecutors);
        Set<Future<OutputMessage>> set = new HashSet<>();

    }

    public ArrayList<Callable<OutputMessage>> createCallables() {
        ArrayList<Callable<OutputMessage>> callables = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            InputMessage iMess = new InputMessage(i, i + 2);
            callables.add(new RandomNumberSeeding(iMess));
        }
        return callables;

    }

    public ArrayList<O> execute(ArrayList<Callable<OutputMessage>> callables) {
        for (int i = 0; i < numThreads; i++) {
            Future<OutputMessage> future = service.submit(callables.get(i));
            set.add(future);
        }

        set.stream().forEach((out) -> {
            try {
                int thread = out.get().getThread();
                double[] list = out.get().getList();
                ArrayList<Double> dlist = new ArrayList<>();
                for (double d : list) {
                    dlist.add(d);
                }

                outputs.set(thread, (O) out.get());
                
                for (double d : list) {
                    //    System.out.println("thread " + thread + " double: " + d);
                }

            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Callabletest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.shutdownNow();
        
        

        for (O o : outputs) {

           // System.out.println("O: " + o);

            OutputMessage out = (OutputMessage)o;
            for (double d : out.getList()) {
               // System.out.println("Thread: " + out.getThread() + " Number: " + d);
            }
        }
        
        return outputs;

    }

}
