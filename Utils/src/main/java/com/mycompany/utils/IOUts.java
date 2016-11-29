/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

/**
 *
 * @author brettsa
 */
class IOUts {

    static String readFileToString(String path, String enc) {

        try {
            Reader in = new InputStreamReader(new FileInputStream(path), enc);
            try {
                return readFully(in);
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public static String readFully(Reader in) throws IOException {

        StringWriter out = new StringWriter();
        copy(in, out);

        return "";
    }

    private static void copy(Reader in, StringWriter out) throws IOException {
        if (out == null) {
            throw new NullPointerException("out");
        }
        char[] buffer = new char[2048];

        for (int read = in.read(buffer); read != -1; read = in.read(buffer)) {
            out.write(buffer, 0, read);
        }
    }

}
