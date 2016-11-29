/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

/**
 *
 * @author brettsa
 */
public class NewClass {

    public static void main(String[] args) {
        String path = "C:\\Users\\brettsa\\Documents\\GitHub\\general\\Test Text.txt";

        String contents = IOUts.readFileToString(path, "UTF-8");

        System.out.println(contents);
    }
}
