/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomthreadpool;

/**
 *
 * @author brettsa
 */
public class OutputMessage {

    private final double[] list;
    private final int thread;

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

}
