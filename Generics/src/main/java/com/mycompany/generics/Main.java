/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generics;

import com.mycompany.generics.blah.CallableItem;
import java.util.ArrayList;
import randomthreadpool.InputMessage;
import randomthreadpool.OutputMessage;
import randomthreadpool.RandomNumberSeeding;

/**
 *
 * @author brettsa
 */
public class Main {
 
    public static void main(String[] args) throws Exception {
        
//        Sorter.run();
//        
//        MaxOfThree.run();
//        
//        Box.run();
        Integer[] intArray = {1, 2, 3, 4, 5};
        ArrayList<Integer[]> input = new ArrayList<>();
        input.add(new Integer[]{1,2,3,4,5});
        input.add(new Integer[]{5,4,5,6,7});
        input.add(new Integer[]{9,8,7,6,5});
        
        
        String s = IOGeneric.run(input);
        
        System.out.println(s);
        
        CallableItem c = new CallableItem();

//        RandomNumberSeeding r = new RandomNumberSeeding(new InputMessage(8,8));
//        Object o = c.run(r);
//        OutputMessage output = (OutputMessage) o;
//        
//        System.out.println(output.getList().length);
        
        
        
        
    }
    
}
