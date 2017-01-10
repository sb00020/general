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

/**
 *
 * @author Sam
 */
public abstract class AbstractThreadPool<O> {

    final int numThreads;
    private final int numExecutors;
    final ExecutorService service;
    Set<Future<O>> set = new HashSet<>();
    //Callable<OutputMessage> callable;
    ArrayList<O> outputs = new ArrayList<>();

    public <I> AbstractThreadPool(int t, int e) {

        numThreads = t;
        numExecutors = e;

        service = Executors.newFixedThreadPool(numExecutors);

    }

    public abstract <I> ArrayList<Callable<O>> createCallables();

    public abstract <I> ArrayList<Callable<O>> createCallables(ArrayList<I> i);

    public ArrayList<O> execute(ArrayList<Callable<O>> callables) {
        for (int i = 0; i < numThreads; i++) {
            Future<O> future = service.submit(callables.get(i));
            set.add(future);
        }

        set.stream().forEach((out) -> {
            try {
                outputs.add(out.get());

            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(AbstractThreadPool.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        service.shutdownNow();

        return outputs;

    }

}
