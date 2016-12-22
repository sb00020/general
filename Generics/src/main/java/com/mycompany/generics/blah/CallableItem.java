/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generics.blah;

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
 * @param <T>
 */
public class CallableItem< T extends CallableWork> {

    int numThreads;
    int numberOfExecutors;
    final ExecutorService service;

    public CallableItem() {
        numThreads = 200;
        numberOfExecutors = 64;

        service = Executors.newFixedThreadPool(numberOfExecutors);

    }

    public Object run(T t) throws Exception {
        ArrayList<OutputMessage> outputs = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            outputs.add(i, new OutputMessage());
        }

        Set<Future<OutputMessage>> set = new HashSet<>();

        for (int i = 0; i < numThreads; i++) {
            InputMessage iMess = new InputMessage(i, i + 2);
            Callable<OutputMessage> callable = t;
            Future<OutputMessage> future = service.submit(callable);
            set.add(future);
        }

        set.stream().forEach((out) -> {
            try {
                int thread = out.get().getThread();
                double[] list = out.get().getList();

                outputs.set(thread, new OutputMessage(list, thread));

                for (double d : list) {
                        System.out.println("thread " + thread + " double: " + d);
                }

            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(Callabletest.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.shutdownNow();

        for (OutputMessage o : outputs) {
            for (double d : o.getList()) {
                System.out.println("Thread: " + o.getThread() + " Number: " + d);
            }
        }
        
        
        
        return outputs;

    }

}
