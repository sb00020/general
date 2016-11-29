/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author brettsa
 */
public class NewClassTest {
    
    public NewClassTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of readFile method, of class NewClass.
     */
    @org.junit.Test
    public void testReadFile() {
        System.out.println("readFile");
        NewClass instance = new NewClass();
        System.out.println(instance.readFile());
    }
    
}
