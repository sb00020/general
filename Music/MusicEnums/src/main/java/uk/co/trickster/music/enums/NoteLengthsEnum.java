/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.trickster.music.enums;

/**
 *
 * @author Admin
 */
public enum NoteLengthsEnum {
    
    EIGHTH(8),
    QUARTER(16),
    HALF(32),
    WHOLE(64);
    
    int ticks;
    
    private NoteLengthsEnum(int ticks){
        this.ticks = ticks;
    }
    
    public int getLength(){
        return ticks;
    }
    
}
