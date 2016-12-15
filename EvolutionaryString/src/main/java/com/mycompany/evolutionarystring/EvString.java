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
    char[] chars;

    public void run() {
        String target = "Hello, World!";
        int targetLength = target.length();

        chars = target.toCharArray();

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

        for (int l = 0; l < 8000; l++) {
            ArrayList<StringAndScore> children = produceChildrenFromFittestParents(scores.get(0), scores.get(1), scores.size());
            Collections.sort(children, new ScoreComparator());

            for (int i = 0; i < children.size(); i++) {

                System.out.println(children.get(i).rndString + "  " + children.get(i).score);

            }

            scores = children;
            System.out.println("");
            System.out.println("");
        }

    }

    private ArrayList<StringAndScore> produceChildrenFromFittestParents(StringAndScore p1, StringAndScore p2, int length) {

        ArrayList<StringAndScore> childrenss = new ArrayList<>();

        System.out.println("p1: " + p1.rndString);
        System.out.println("p2: " + p2.rndString);

        for (int j = 0; j < length; j++) {

            char[] child = p1.rndString.toCharArray();

            int p2Dom = rnd.nextInt(length-1);

            for (int i = 0; i < p2Dom; i++) {

                int c1 = rnd.nextInt(p1.getRndString().length());
                int c2 = rnd.nextInt(p1.getRndString().length());

                child[c1] = p2.getRndString().toCharArray()[c2];

            }
            
            child = mutateChild(child);
            
            childrenss.add(new StringAndScore(new String(child), StringAndScore.getScore(chars, child)));

        }

        return childrenss;
    }

    private char[] mutateChild(char[] child) {
        
        for (int i = 0; i< 3; i++) {
            int rndChar = rnd.nextInt(child.length);
            
            child[rndChar] = randomChar();
            
        }
        
        return child;
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

    private char randomChar() {
        return (char) (rnd.nextInt(127) + 32);
    }

    private ArrayList<char[]> createInitialParents(int num, int targetLength) {

        ArrayList<char[]> parents = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            parents.add(this.generateRandomCharArray(targetLength));
        }

        return parents;

    }

    public int getScore(char[] target, char[] attempt) {
        int score = 0;
        for (int i = 0; i < target.length; i++) {
            int s = (int) (target[i] - attempt[i]);
            score += Math.abs(s);
        }
        return score;
    }

}
