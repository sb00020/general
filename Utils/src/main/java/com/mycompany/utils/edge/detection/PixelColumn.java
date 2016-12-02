/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils.edge.detection;

import java.util.ArrayList;

/**
 *
 * @author brettsa
 */
public class PixelColumn {
    
    final ArrayList<RGBValues> column;// = new ArrayList<RGBValues>();
    
    public PixelColumn (int length){
        column = new ArrayList<RGBValues>(length);
    }
    public void add(int index, byte pixel){
        column.add(index, new RGBValues(pixel));
        
    }
    
}
