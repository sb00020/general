/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generics;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import randomthreadpool.InputMessage;
import randomthreadpool.OutputMessage;
import randomthreadpool.RandomNumberSeeding;

/**
 *
 * @author Sam
 */
public class ConcreteThreadPool extends AbstractThreadPool {

    public <I extends Object, O extends Object> ConcreteThreadPool(int t, int e) {
        super(t, e);
    }

    @Override
    public ArrayList createCallables() {
        ArrayList<Callable<OutputMessage>> callables = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            InputMessage iMess = new InputMessage(i, i + 2);
            callables.add(new RandomNumberSeeding(iMess));
        }
        return callables;
    }

    @Override
    public ArrayList createCallables(ArrayList input) {
        ArrayList<Callable<OutputMessage>> callables = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            InputMessage iMess = (InputMessage) input.get(i);
            callables.add(new RandomNumberSeeding(iMess));
        }
        return callables;
    }

}
