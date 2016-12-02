/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils.edge.detection;

/**
 *
 * @author brettsa
 */
public class RGBValues {

    private final int red, blue, green;

    public RGBValues(byte rgb) {

        red = (rgb >> 16) & 0x0ff;
        green = (rgb >> 8) & 0x0ff;
        blue = (rgb) & 0x0ff;

    }

    public RGBValues(int red, int blue, int green) {

        this.red = red;
        this.blue = blue;
        this.green = green;

    }

    public int getRed() {
        return red;
    }

    public int getBlue() {
        return blue;
    }

    public int getGreen() {
        return green;
    }

}
