/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomthreadpool;

import com.mycompany.generics.messages.AbstractOutputMessage;

/**
 *
 * @author brettsa
 */
public class OutputMessage extends AbstractOutputMessage{


    private final double[] list;

    public double[] getList() {
        return list;
    }
 
    public int getThread() {
        return thread;
    } 

    public OutputMessage(double[] list, int thread) {
        this.list = list;
        this.thread = thread;
    }

    public OutputMessage() {
        this.list = null;
        this.thread = 0;
    }

    @Override
    public int compareTo(AbstractOutputMessage o) {       
        
        return this.thread - o.thread;
        
    }
    
    

}
