/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils.edge.detection;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;

/**
 *
 * @author brettsa
 */
public class PixelMap {

    ArrayList<PixelColumn> map = new ArrayList<>();

    public void add(final int index, final PixelColumn col) {
        map.add(index, col);
    }

    public static PixelMap convertToPixelMap(BufferedImage image) {

        final PixelMap pixelMap = new PixelMap();
        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        final int width = image.getWidth();
        final int height = image.getHeight();
        final boolean hasAlphaChannel = image.getAlphaRaster() != null;

        int[][] result = new int[height][width];
        if (hasAlphaChannel) {
            final int pixelLength = 4;
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {

                System.out.println(row + " " + col);

                int argb = 0;
                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
                argb += ((int) pixels[pixel + 1] & 0xff); // blue
                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
                result[row][col] = argb;
                col++;
                if (col == width) {
                    col = 0;
                    row++;
                }
            }
        } else {
            final int pixelLength = 3;
            PixelColumn column = new PixelColumn(width);
            for (int pixel = 0, row = 0, col = 0; pixel < pixels.length; pixel += pixelLength) {
                int argb = 0;

                System.out.println("Row/Col: " + row + " " + col + " " + (pixels[pixel] & 0x0ff) );

                column.add(col, pixels[pixel]);

                col++;
                if (col == width) {

                    pixelMap.add(row, column);

                    column = new PixelColumn(width);

                    col = 0;
                    row++;
                }
            }
        }

        return pixelMap;
    }

}
