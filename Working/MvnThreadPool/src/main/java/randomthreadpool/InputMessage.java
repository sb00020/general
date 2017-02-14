/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomthreadpool;

import com.mycompany.generics.messages.AbstractInputMessage;

/**
 *
 * @author brettsa
 */
public class InputMessage extends AbstractInputMessage {

    private final int seed, thread;

    public InputMessage(int thread, int seed) {
        this.seed = seed;
        this.thread = thread;
    }

    public int getSeed() {
        return seed;
    }

    public int getThread() {
        return thread;
    }

}
