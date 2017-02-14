/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.generics.messages;

/**
 *
 * @author brettsa
 */
public abstract class AbstractOutputMessage implements Comparable<AbstractOutputMessage>{
    
    String status;
    public int thread;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
