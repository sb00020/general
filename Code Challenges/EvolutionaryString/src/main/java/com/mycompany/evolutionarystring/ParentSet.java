/*
 * Permission to reproduce, host or copy is forbidden. 
 * Please request in writing and reasonable requests considered.
 * Trickster Licensing.
 */
package com.mycompany.evolutionarystring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Sam
 */
public class ParentSet {

    ArrayList<StringAndScore> parents = new ArrayList<>();

    public ParentSet(StringAndScore... ps) {
        parents.addAll(Arrays.asList(ps));
    }

    public ArrayList<StringAndScore[]> getParentPairs() {

        Random rnd = new Random();
        int i = rnd.nextInt(3) + 1;

        StringAndScore[] couple1 = new StringAndScore[2];
        StringAndScore[] couple2 = new StringAndScore[2];

        couple1[0] = parents.get(0);
        couple1[1] = parents.get(i);

        int count = 0;
        for (int j = 1; i <= parents.size(); j++) {
            if (j != i) {

                couple2[count] = parents.get(j);
                count++;
            }
            if (count == 2) {
                break;
            }
        }

        for (StringAndScore s : parents) {
        //    System.out.println("Parents: " + s.getRndString());
        }

        ArrayList<StringAndScore[]> couples = new ArrayList<>();
        couples.add(couple1);
        couples.add(couple2);
        
        return couples;
        
    }

}
