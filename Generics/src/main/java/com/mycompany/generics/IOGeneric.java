/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generics;

import java.util.ArrayList;

/**
 *
 * @author brettsa
 */
public class IOGeneric {

    // generic method printArray
    public static <E> String printArray(E[] inputArray) {
        // Display array elements
        String s = "";

        for (E element : inputArray) {
            System.out.printf("%s ", element);
            s += element + " ";
        }
        System.out.println();
        return s;
    }

    public static String run(ArrayList<Integer[]> inputs) {
        String output = "";
        for (Integer[] i : inputs) {

            // Integer[] intArray = {1, 2, 3, 4, 5};
            output += printArray(i);

        }
        return output;
    }

}
