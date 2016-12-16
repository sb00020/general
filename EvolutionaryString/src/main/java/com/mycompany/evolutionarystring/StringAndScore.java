/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.evolutionarystring;

/**
 *
 * @author brettsa
 */
public class StringAndScore {
    int score;
    String rndString;

    StringAndScore(String string, int score) {
       this.score = score;
       this.rndString = string;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRndString() {
        return rndString;
    }

    public void setRndString(String rndString) {
        this.rndString = rndString;
    }
    
    public static int getScore(char[] target, char[] attempt) {
        int score = 0;
        for (int i = 0; i < target.length; i++) {
            int s = (int) (target[i] - attempt[i]);
            score += Math.abs(s);
        }
        return score;
    }
    
}
