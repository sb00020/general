/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.evolutionarystring;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author brettsa
 */
public class EvString {

    Random rnd = new Random(System.currentTimeMillis());

    public void run() {
        String target = "Hello, World!";
        int targetLength = target.length();

        char[] chars = target.toCharArray();

        ArrayList<char[]> parents = createInitialParents(8, targetLength);

        ArrayList<StringAndScore> scores = new ArrayList<>();
        
        for (int i = 0; i < parents.size(); i++) {
            
            String s = new String(parents.get(i));
            int score = this.getScore(target.toCharArray(), parents.get(i));
            
            scores.add(new StringAndScore(s, score));

        }
        
Collections.sort(scores, new ScoreComparator());
        for (int i = 0; i < parents.size(); i++) {
            
            System.out.println(scores.get(i).rndString + scores.get(i).score);

        }

    }

    class ScoreComparator implements Comparator<StringAndScore> {
    @Override
    public int compare(StringAndScore a, StringAndScore b) {
        return a.score < b.score ? -1 : a.score == b.score ? 0 : 1;
    }
}
    
    public char[] generateRandomCharArray(int length) {

        char[] parent = new char[length];
        for (int i = 0; i < length; i++) {
            parent[i] = (char) (rnd.nextInt(127) + 32);
        }
        return parent;
    }

    private ArrayList<char[]> createInitialParents(int num, int targetLength) {

        ArrayList<char[]> parents = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            parents.add(this.generateRandomCharArray(targetLength));
        }

        return parents;

    }

    private int getScore(char[] target, char[] attempt) {
        int score = 0;
        for (int i = 0; i < target.length; i++) {
            int s = (int) (target[i] - attempt[i]);
            score += Math.abs(s);
        }
        return score;
    }

}
