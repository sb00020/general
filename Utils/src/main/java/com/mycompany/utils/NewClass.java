/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import java.io.File;

/**
 *
 * @author brettsa
 */
public class NewClass {

    public static void main(String[] args) {
        
    }

    public String readFile() {
        String path = "C:\\Users\\brettsa\\Documents\\GitHub\\general\\TestText.txt";
        File f = new File(path);
        
        System.out.println(f.exists());
        
        String contents = IOUts.readFileToString(path, "UTF-8");

        return contents;
    }

}
