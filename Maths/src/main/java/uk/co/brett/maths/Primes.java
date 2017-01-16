/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.brett.maths;

import java.util.ArrayList;

/**
 *
 * @author brettsa
 */
class Primes {

    static void find(int max) {

        int sqrtMax = (int) Math.ceil(Math.sqrt(max))+10;

        ArrayList<Integer> list = new ArrayList<>();
        list.add(2);
        for (int i = 2; i <= max; i++) {

            if (i % 2 != 0) {

                for (int j = 3; j <= sqrtMax; j = j + 2) {
                    //System.out.println(i + "  " + j);
                    if (i != j) {
                        if (i % j == 0) {
                            break;
                        }

                        if (j >= sqrtMax-1) {
                            list.add(j);
                            System.out.println(i);
                        }
                    }
                }

            }

        }

        System.out.println(list.size());
        
    }

}
